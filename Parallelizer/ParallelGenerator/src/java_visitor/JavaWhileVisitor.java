package java_visitor;

import java.util.List;

import model.expression.Expression;
import model.statement.Statement;
import visitor.WhileVisitor;

public class JavaWhileVisitor implements WhileVisitor {
	public void execute(Expression condition, List<Statement> statements) {
		System.out.println("while (" + condition.evaluate() + ") {");

		for (Statement s : statements) {
			System.out.print("    ");
			s.execute();
		}

		System.out.println("}");
	}
}
