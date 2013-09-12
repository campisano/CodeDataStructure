package simple_model.statement;

import simple_model.expression.Expression;

public class Print implements Statement {

	private Expression expression;

	public Print(Expression expression) {
		this.expression = expression;
	}

	public void execute() {
		System.out.println("std::cout << " + expression.evaluate() + ";");
	}
}
