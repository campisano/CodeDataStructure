package visitor_imp.java_visitor;

import model.expression.Expression;
import visitor.PrintVisitor;

public class JavaPrintVisitor implements PrintVisitor {
	public void execute(Expression expression) {
		System.out.println("System.out.print(" + expression.evaluate() + ");");
	}
}
