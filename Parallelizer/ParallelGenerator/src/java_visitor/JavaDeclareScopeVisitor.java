package java_visitor;

import visitor.DeclareScopeVisitor;

public class JavaDeclareScopeVisitor implements DeclareScopeVisitor {
	public void execute(String scopeName, String valueName) {
		System.out.println(scopeName + " " + valueName + ";");
	}
}
