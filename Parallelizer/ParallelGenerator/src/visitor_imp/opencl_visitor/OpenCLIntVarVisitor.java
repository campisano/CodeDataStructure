package visitor_imp.opencl_visitor;

import visitor.IntVarVisitor;

public class OpenCLIntVarVisitor implements IntVarVisitor {
	public void execute(String name, Integer value, boolean constant) {
		if (constant) {
			System.out.print("const ");
		}

		if (value == null) {
			System.out.println("int " + name + ";");
		} else {
			System.out.println("int " + name + " = " + value + ";");
		}
	}
}
