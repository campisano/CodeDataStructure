package simple_model.statement;

import java.util.ArrayList;
import java.util.List;

import simple_model.expression.Expression;

public class While implements Statement {
	private Expression condition;
	private List<Statement> statements;

	public While(Expression condition, Statement... statements) {
		this.condition = condition;
		this.statements = new ArrayList<Statement>();

		for (Statement s : statements) {
			this.statements.add(s);
		}
	}

	public void execute() {
		System.out.println("while (" + condition.evaluate() + ") {");

		for (Statement s : statements) {
			System.out.print("    ");
			s.execute();
		}

		System.out.println("}");
	}
}
