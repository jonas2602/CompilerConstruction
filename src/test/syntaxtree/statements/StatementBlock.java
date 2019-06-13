package test.syntaxtree.statements;

import gen.PascalParser;
import test.syntaxtree.BlockNode;

import java.util.List;

public class StatementBlock {
    public static void processStatementBlock(PascalParser.CompoundStatementContext ctx, BlockNode parent) {
        List<PascalParser.StatementContext> stmblocks = ctx.statements().statement();
        for(PascalParser.StatementContext stm: stmblocks) {
            if(stm.label() != null) {
                LabelDefinitionNode label = new LabelDefinitionNode(parent);
                label.buildAST(stm.label());
                parent.addStatement(label);
            }

            //unlabelled statements
            PascalParser.UnlabelledStatementContext unstm = stm.unlabelledStatement();
            if(unstm.structuredStatement() != null) {
                PascalParser.StructuredStatementContext ststm = unstm.structuredStatement();
                if(ststm.compoundStatement() != null) {
                    StatementBlock.processStatementBlock(ststm.compoundStatement(), parent);
                }

                // ...
            }
            else {
                PascalParser.SimpleStatementContext simst = unstm.simpleStatement();
                if(simst.emptyStatement() != null) {
                    continue;
                }

                if(simst.gotoStatement() != null) {
                    GotoStatementNode jmp = new GotoStatementNode(parent);
                    jmp.buildAST(simst.gotoStatement());
                    parent.addStatement(jmp);
                }
            }
        }
    }
}
