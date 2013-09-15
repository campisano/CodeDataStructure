package cpp_visitor;

import java.util.List;

import simple_model.expression.Expression;
import simple_model.statement.Statement;
import visitor.WhileVisitor;

public class CppWhileVisitor implements WhileVisitor {
	public void execute(Expression condition, List<Statement> statements) {
		System.out.println("while (" + condition.evaluate() + ") {");

		for (Statement s : statements) {
			System.out.print("    ");
			s.execute();
		}

		System.out.println("}");
	}
}
