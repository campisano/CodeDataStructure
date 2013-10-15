package visitor_imp.opencl_visitor;

import model.expression.Value;
import visitor.ArrayIntVisitor;

public class OpenCLArrayIntVisitor implements ArrayIntVisitor {
	public void execute(String name, Value size) {
		System.out.println("int " + name + "[" + size.evaluate() + "];");
	}
}
