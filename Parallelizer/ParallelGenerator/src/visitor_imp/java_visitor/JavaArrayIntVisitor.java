package visitor_imp.java_visitor;

import model.expression.Value;
import visitor.ArrayIntVisitor;

public class JavaArrayIntVisitor implements ArrayIntVisitor {
	public void execute(String name, Value size) {
		System.out.println("int[] " + name + " = new int[" + size.evaluate()
				+ "];");
	}
}
