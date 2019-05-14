package FirstGrammar.parser.visitors;

import java.util.LinkedList;
import java.util.List;

import FirstGrammar.parser.tree.RuleNode;
import FirstGrammar.parser.tree.rules.Rule_CONSTANT;
import FirstGrammar.parser.tree.rules.Rule_EXP;
import FirstGrammar.parser.tree.rules.Rule_EXPPRIME;
import FirstGrammar.parser.tree.rules.Rule_STM;

public class PrettyPrintVisitor {

	public List<String> visit(RuleNode ctx) {
		if(ctx instanceof Rule_STM) return visitRule_STM((Rule_STM)ctx);
		else if(ctx instanceof Rule_EXP) return visitRule_EXP((Rule_EXP)ctx);
		else if(ctx instanceof Rule_EXPPRIME) return visitRule_EXPPRIME((Rule_EXPPRIME)ctx);
		else if(ctx instanceof Rule_CONSTANT) return visitRule_CONSTANT((Rule_CONSTANT)ctx);
		else {
			System.out.println("unexpected class type " + ctx);
			return new LinkedList<String>();
		}
	}
	
	private List<String> visitRule_STM(Rule_STM ctx) {		
		if(ctx.getExp() != null){
			return visit(ctx.getExp());			
		}
		else {
			List<String> outLines = new LinkedList<String>();
			outLines.add("if " + ctx.getID().getValue());
			outLines.add("then");
			for(String line : visit(ctx.getThenStm())) {
				outLines.add("\t" + line);
			}
			outLines.add("else");
			for (String line : visit(ctx.getElseStm())) {
				outLines.add("\t" + line);
			}
			
			return outLines;
		}
	}
	
	private List<String> visitRule_EXP(Rule_EXP ctx) {
		String expString = "";
		
		if(ctx.getConstant() != null) {
			expString += visit(ctx.getConstant()).get(0);
		}
		if(ctx.getExpprime() != null) {
			expString += visit(ctx.getExpprime()).get(0);	
		}
		
		List<String> outList = new LinkedList<String>();
		outList.add(expString);
		return outList;
	}

	private List<String> visitRule_EXPPRIME(Rule_EXPPRIME ctx) {
		String expPrimeString = "";
		
		if(ctx.getConstant() != null) {
			expPrimeString += " + " + visit(ctx.getConstant()).get(0);
		}
		if(ctx.getExpprime() != null) {
			expPrimeString += visit(ctx.getExpprime()).get(0);	
		}

		List<String> outList = new LinkedList<String>();
		outList.add(expPrimeString);
		return outList;
	}

	private List<String> visitRule_CONSTANT(Rule_CONSTANT ctx) {
		if(ctx.getExp() != null){
			List<String> outList = new LinkedList<String>();
			outList.add("(" + visit(ctx.getExp()).get(0) + ")");
			return outList;
		}
		else if(ctx.getId() != null) {	
			List<String> outList = new LinkedList<String>();
			outList.add(ctx.getId().getValue());
			return outList;
		}
		else if(ctx.getNum() != null) {
			List<String> outList = new LinkedList<String>();
			outList.add(ctx.getNum().getValue());
			return outList;
		}
		else {
			return new LinkedList<String>();
		}	
	}
}
