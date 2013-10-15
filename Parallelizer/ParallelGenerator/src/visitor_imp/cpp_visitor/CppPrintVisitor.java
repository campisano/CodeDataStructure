package visitor_imp.cpp_visitor;

import model.expression.Expression;
import visitor.PrintVisitor;

public class CppPrintVisitor implements PrintVisitor {
	public void execute(Expression expression) {
		System.out.println("std::cout << " + expression.evaluate() + ";");
	}
}
