package java_visitor;

import simple_model.expression.Expression;
import visitor.PrintVisitor;

public class JavaPrintVisitor implements PrintVisitor {
	public void execute(Expression expression) {
		System.out.println("System.out.print(" + expression.evaluate() + ");");
	}
}
