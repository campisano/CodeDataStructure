package cpp_visitor;

import visitor.DeclareScopeVisitor;

public class CppDeclareScopeVisitor implements DeclareScopeVisitor {
	public void execute(String scopeName, String valueName) {
		System.out.println(scopeName + " " + valueName + ";");
	}
}
