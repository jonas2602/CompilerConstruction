// Generated from D:/JetBrains/Projects/Intellij/CompilerConstruction/src\Gram.g4 by ANTLR 4.7.2
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GramParser}.
 */
public interface GramListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GramParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GramParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#additive}.
	 * @param ctx the parse tree
	 */
	void enterAdditive(GramParser.AdditiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#additive}.
	 * @param ctx the parse tree
	 */
	void exitAdditive(GramParser.AdditiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#sum}.
	 * @param ctx the parse tree
	 */
	void enterSum(GramParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#sum}.
	 * @param ctx the parse tree
	 */
	void exitSum(GramParser.SumContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#subtract}.
	 * @param ctx the parse tree
	 */
	void enterSubtract(GramParser.SubtractContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#subtract}.
	 * @param ctx the parse tree
	 */
	void exitSubtract(GramParser.SubtractContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#multiplicative}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative(GramParser.MultiplicativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#multiplicative}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative(GramParser.MultiplicativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(GramParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(GramParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#divide}.
	 * @param ctx the parse tree
	 */
	void enterDivide(GramParser.DivideContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#divide}.
	 * @param ctx the parse tree
	 */
	void exitDivide(GramParser.DivideContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(GramParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(GramParser.ConstantContext ctx);
}