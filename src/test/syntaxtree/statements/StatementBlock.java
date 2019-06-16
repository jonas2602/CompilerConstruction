package test.syntaxtree.statements;

import gen.PascalParser;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.statements.calls.ProcedureCall;
import test.syntaxtree.statements.controlstatements.IfStatement;
import test.syntaxtree.statements.controlstatements.RepeatStatement;
import test.syntaxtree.statements.controlstatements.WhileStatement;

import java.util.List;

public class StatementBlock {
    public static void processSingleStatement(PascalParser.StatementContext ctx, ScopeNode parent) {
        if(ctx.label() != null) {
            LabelDefinitionNode label = new LabelDefinitionNode(parent);
            label.buildAST(ctx.label());
            parent.addStatement(label);
        }

        //unlabelled statements
        PascalParser.UnlabelledStatementContext unstm = ctx.unlabelledStatement();
        if(unstm.structuredStatement() != null) {
            PascalParser.StructuredStatementContext ststm = unstm.structuredStatement();
            if(ststm.compoundStatement() != null) {
                StatementBlock.processStatementBlock(ststm.compoundStatement(), parent);
            }
            else if(ststm.conditionalStatement() != null) {
                PascalParser.ConditionalStatementContext conctx = ststm.conditionalStatement();
                if(conctx.ifStatement() != null) {
                    IfStatement ifstm = new IfStatement(parent);
                    ifstm.buildAST(conctx.ifStatement());
                    parent.addStatement(ifstm);
                }
                //TODO: case
            }
            else if(ststm.repetetiveStatement() != null) {
                PascalParser.RepetetiveStatementContext repctx = ststm.repetetiveStatement();
                if(repctx.whileStatement() != null) {
                    WhileStatement whilestm = new WhileStatement(parent);
                    whilestm.buildAST(repctx.whileStatement());
                    parent.addStatement(whilestm);
                }
                else if(repctx.repeatStatement() != null) {
                    RepeatStatement repstm = new RepeatStatement(parent);
                    repstm.buildAST(repctx.repeatStatement());
                    parent.addStatement(repstm);
                }
                else if(repctx.forStatement() != null) {
                    //TODO: for
                }
            }
        }
        else {
            PascalParser.SimpleStatementContext simst = unstm.simpleStatement();
            if(simst.emptyStatement() != null) {

            }
            else if(simst.gotoStatement() != null) {
                GotoStatementNode jmp = new GotoStatementNode(parent);
                jmp.buildAST(simst.gotoStatement());
                parent.addStatement(jmp);
            }
            else if(simst.assignmentStatement() != null) {
                AssignmentStatementNode assignment = new AssignmentStatementNode(parent);
                assignment.buildAST(simst.assignmentStatement());
                parent.addStatement(assignment);
            }
            else if(simst.procedureStatement() != null) {
                ProcedureCall proc = new ProcedureCall(parent);
                proc.buildAST(simst.procedureStatement());
                parent.addStatement(proc);
            }
        }
    }

    public static void processStatementBlock(PascalParser.CompoundStatementContext ctx, ScopeNode parent) {
        List<PascalParser.StatementContext> stmblocks = ctx.statements().statement();
        for(PascalParser.StatementContext stm: stmblocks) {
            processSingleStatement(stm, parent);
        }
    }
}
