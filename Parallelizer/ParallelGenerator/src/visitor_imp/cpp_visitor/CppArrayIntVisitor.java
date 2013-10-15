package visitor_imp.cpp_visitor;

import model.expression.Value;
import visitor.ArrayIntVisitor;

public class CppArrayIntVisitor implements ArrayIntVisitor {
	public void execute(String name, Value size) {
		System.out.println("int " + name + "[" + size.evaluate() + "];");
	}
}
