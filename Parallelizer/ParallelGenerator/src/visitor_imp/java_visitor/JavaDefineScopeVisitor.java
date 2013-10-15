package visitor_imp.java_visitor;

import java.util.List;

import model.statement.Statement;
import visitor.DefineScopeVisitor;

public class JavaDefineScopeVisitor implements DefineScopeVisitor {
	public void execute(String scopeName, List<Statement> statements) {
		System.out.println("public class " + scopeName + " {");

		for (Statement s : statements) {
			System.out.print("    public ");
			s.execute();
		}

		System.out.println("}");
	}
}
