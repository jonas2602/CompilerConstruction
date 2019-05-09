// Generated from D:/JetBrains/Projects/Intellij/CompilerConstruction/src\Gram.g4 by ANTLR 4.7.2
package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GramParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GramVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(GramParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#additive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive(GramParser.AdditiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#sum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum(GramParser.SumContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#subtract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtract(GramParser.SubtractContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#multiplicative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative(GramParser.MultiplicativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#product}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProduct(GramParser.ProductContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#divide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(GramParser.DivideContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(GramParser.ConstantContext ctx);
}