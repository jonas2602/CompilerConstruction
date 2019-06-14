package visitors;

import ast.AbstractSyntaxTree;
import ast.expression.ParameterNode;
import ast.expression.VariableNode;
import ast.statement.*;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;
import java.util.List;

public class StatementVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    @Override
    public AbstractSyntaxTree visitStatement(PascalParser.StatementContext ctx) {
        // TODO: labeled statements
        return visitUnlabelledStatement(ctx.unlabelledStatement());
    }

    @Override
    public AbstractSyntaxTree visitUnlabelledStatement(PascalParser.UnlabelledStatementContext ctx) {
        if (ctx.simpleStatement() != null) {
            return visitSimpleStatement(ctx.simpleStatement());
        } else {
            return visitStructuredStatement(ctx.structuredStatement());
        }
    }

    @Override
    public AbstractSyntaxTree visitSimpleStatement(PascalParser.SimpleStatementContext ctx) {
        if (ctx.assignmentStatement() != null) {
            return visitAssignmentStatement(ctx.assignmentStatement());
        } else if (ctx.procedureStatement() != null) {
            return visitProcedureStatement(ctx.procedureStatement());
        } else if (ctx.gotoStatement() != null) {
            return visitGotoStatement(ctx.gotoStatement());
        } else { // Empty Statement
            return null;
        }
    }

    @Override
    public AbstractSyntaxTree visitAssignmentStatement(PascalParser.AssignmentStatementContext ctx) {
        AbstractSyntaxTree variable = new VariableAccessVisitor().visit(ctx.variable());
        AbstractSyntaxTree expression = new ExpressionVisitor().visit(ctx.expression());
        return new AssignmentNode(variable, expression);
    }

    @Override
    public AbstractSyntaxTree visitProcedureStatement(PascalParser.ProcedureStatementContext ctx) {
        String procName = ctx.identifier().IDENT().getText();
        List<AbstractSyntaxTree> params = new ArrayList<>();
        if (ctx.parameterList() != null) {
            params = new ParameterVisitor().visit(ctx.parameterList());
        }

        ProcCallNode procCall = new ProcCallNode(procName, params);
        return procCall;
    }

    @Override
    public AbstractSyntaxTree visitGotoStatement(PascalParser.GotoStatementContext ctx) {
        return new GotoNode(ctx.label().getText());
    }

    @Override
    public AbstractSyntaxTree visitStructuredStatement(PascalParser.StructuredStatementContext ctx) {
        if (ctx.compoundStatement() != null) {
            return visitCompoundStatement(ctx.compoundStatement());
        } else if (ctx.conditionalStatement() != null) {
            return visitConditionalStatement(ctx.conditionalStatement());
        } else if (ctx.repetetiveStatement() != null) {
            return visitRepetetiveStatement(ctx.repetetiveStatement());
        } else {
            return visitWithStatement(ctx.withStatement());
        }
    }

    @Override
    public AbstractSyntaxTree visitCompoundStatement(PascalParser.CompoundStatementContext ctx) {
        return visitStatements(ctx.statements());
    }

    @Override
    public AbstractSyntaxTree visitStatements(PascalParser.StatementsContext ctx) {
        CompStmtNode compStmt = new CompStmtNode();
        // The last Statement is always an empty statement. Therefore if the amount of stmts is not
        // more than 2 (actually 1 valid) its not necessary to combine them in a compound statement
        if (ctx.statement().size() <= 2) {
            return visitStatement(ctx.statement(0));
        }

        for (int i = 0; i < ctx.statement().size() - 1; i++) {
            AbstractSyntaxTree stmtNode = visitStatement(ctx.statement(i));
            compStmt.AddStatement(stmtNode);
        }

        return compStmt;
    }

    @Override
    public AbstractSyntaxTree visitConditionalStatement(PascalParser.ConditionalStatementContext ctx) {
        if (ctx.ifStatement() != null) {
            return visitIfStatement(ctx.ifStatement());
        } else {
            return visitCaseStatement(ctx.caseStatement());
        }
    }

    @Override
    public AbstractSyntaxTree visitRepetetiveStatement(PascalParser.RepetetiveStatementContext ctx) {
        if (ctx.whileStatement() != null) {
            return visitWhileStatement(ctx.whileStatement());
        } else if (ctx.repeatStatement() != null) {
            return visitRepeatStatement(ctx.repeatStatement());
        } else {
            return visitForStatement(ctx.forStatement());
        }
    }

    @Override
    public AbstractSyntaxTree visitWithStatement(PascalParser.WithStatementContext ctx) {
        AbstractSyntaxTree statement = visitStatement(ctx.statement());
        ArrayList<AbstractSyntaxTree> variableList = new ArrayList<AbstractSyntaxTree>();
        for (PascalParser.VariableContext var : ctx.recordVariableList().variable()) {
            variableList.add(new VariableAccessVisitor().visit(var));
        }

        return new WithNode(variableList.toArray(AbstractSyntaxTree[]::new), statement);
    }

    @Override
    public AbstractSyntaxTree visitIfStatement(PascalParser.IfStatementContext ctx) {
        AbstractSyntaxTree condition = new ExpressionVisitor().visit(ctx.expression());
        AbstractSyntaxTree thenStatement = visitStatement(ctx.statement(0));
        AbstractSyntaxTree elseStatement = null;
        if (ctx.ELSE() != null) {
            elseStatement = visitStatement(ctx.statement(1));
        }

        return new BranchNode(condition, thenStatement, elseStatement);
    }

    @Override
    public AbstractSyntaxTree visitCaseStatement(PascalParser.CaseStatementContext ctx) {
        // TODO
        return null;
    }

    @Override
    public AbstractSyntaxTree visitWhileStatement(PascalParser.WhileStatementContext ctx) {
        AbstractSyntaxTree condition = new ExpressionVisitor().visit(ctx.expression());
        AbstractSyntaxTree body = visitExpression(ctx.expression());

        return new WhileNode(condition, body);
    }

    @Override
    public AbstractSyntaxTree visitRepeatStatement(PascalParser.RepeatStatementContext ctx) {
        AbstractSyntaxTree condition = new ExpressionVisitor().visit(ctx.expression());
        AbstractSyntaxTree body = visitExpression(ctx.expression());

        return new RepeatNode(condition, body);
    }

    @Override
    public AbstractSyntaxTree visitForStatement(PascalParser.ForStatementContext ctx) {
        VariableNode variable = new VariableNode(ctx.identifier().IDENT().getText());
        AbstractSyntaxTree initialValue = new ExpressionVisitor().visit(ctx.initialValue().expression());
        AbstractSyntaxTree finalValue = new ExpressionVisitor().visit(ctx.finalValue().expression());
        boolean bIncrement = ctx.TO() != null;
        AbstractSyntaxTree body = visitStatement(ctx.statement());

        // TODO: Create Block Node because new Variable (Iterator) created? Not Allowed?
        return new ForNode(variable, initialValue, finalValue, bIncrement, body);
    }
}
