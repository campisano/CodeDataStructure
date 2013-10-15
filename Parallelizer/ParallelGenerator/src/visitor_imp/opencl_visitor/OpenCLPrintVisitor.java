package visitor_imp.opencl_visitor;

import model.expression.Expression;
import visitor.PrintVisitor;

public class OpenCLPrintVisitor implements PrintVisitor {
	public void execute(Expression expression) {
		System.out.println("std::cout << " + expression.evaluate() + ";");
	}
}
