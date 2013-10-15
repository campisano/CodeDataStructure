package visitor_imp.opencl_visitor;

import visitor.DeclareScopeVisitor;

public class OpenCLDeclareScopeVisitor implements DeclareScopeVisitor {
	public void execute(String scopeName, String valueName) {
		System.out.println(scopeName + " " + valueName + ";");
	}
}
