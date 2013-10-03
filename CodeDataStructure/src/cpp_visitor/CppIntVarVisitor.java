package cpp_visitor;

import visitor.IntVarVisitor;

public class CppIntVarVisitor implements IntVarVisitor {
	public void execute(String name) {
		System.out.println("int " + name + ";");
	}
}
