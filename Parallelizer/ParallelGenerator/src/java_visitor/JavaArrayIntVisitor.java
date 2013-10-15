package java_visitor;

import visitor.ArrayIntVisitor;

public class JavaArrayIntVisitor implements ArrayIntVisitor {
	public void execute(String name, int size) {
		System.out.println("int[] " + name + " = new int[" + size + "];");
	}
}
