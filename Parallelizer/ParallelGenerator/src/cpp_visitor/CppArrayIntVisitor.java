package cpp_visitor;

import visitor.ArrayIntVisitor;

public class CppArrayIntVisitor implements ArrayIntVisitor {
	public void execute(String name, int size) {
		System.out.println("int " + name + "[" + size + "];");
	}
}
