package FirstGrammar;

import java.util.LinkedList;
import java.util.List;

public class Visitor {

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
		if(ctx.exp != null){
			return visit(ctx.exp);			
		}
		else {
			List<String> outLines = new LinkedList<String>();
			outLines.add("if " + ctx.ID.getText());
			outLines.add("then");
			for(String line : visit(ctx.thenStm)) {
				outLines.add("\t" + line);
			}
			outLines.add("else");
			for (String line : visit(ctx.elseStm)) {
				outLines.add("\t" + line);
			}
			
			return outLines;
		}
	}
	
	private List<String> visitRule_EXP(Rule_EXP ctx) {
		String expString = "";
		
		if(ctx.constant != null) {
			expString += visit(ctx.constant).get(0);
		}
		if(ctx.expprime != null) {
			expString += visit(ctx.expprime).get(0);	
		}
		
		List<String> outList = new LinkedList<String>();
		outList.add(expString);
		return outList;
	}

	private List<String> visitRule_EXPPRIME(Rule_EXPPRIME ctx) {
		String expPrimeString = "";
		
		if(ctx.constant != null) {
			expPrimeString += " + " + visit(ctx.constant).get(0);
		}
		if(ctx.expprime != null) {
			expPrimeString += visit(ctx.expprime).get(0);	
		}

		List<String> outList = new LinkedList<String>();
		outList.add(expPrimeString);
		return outList;
	}

	private List<String> visitRule_CONSTANT(Rule_CONSTANT ctx) {
		if(ctx.exp != null){
			List<String> outList = new LinkedList<String>();
			outList.add("(" + visit(ctx.exp).get(0) + ")");
			return outList;
		}
		else if(ctx.ID != null) {	
			List<String> outList = new LinkedList<String>();
			outList.add(ctx.ID.getText());
			return outList;
		}
		else if(ctx.NUM != null) {
			List<String> outList = new LinkedList<String>();
			outList.add(ctx.NUM.getText());
			return outList;
		}
		else {
			return new LinkedList<String>();
		}	
	}
}
