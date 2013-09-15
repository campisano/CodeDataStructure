package java_visitor;

import visitor.IntVarVisitor;

public class JavaIntVarVisitor implements IntVarVisitor {
	public void execute(String name) {
		System.out.println("int " + name + ";");
	}
}
