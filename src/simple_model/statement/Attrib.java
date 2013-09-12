package simple_model.statement;

import simple_model.expression.Expression;
import simple_model.expression.Value;

public class Attrib implements Statement {
	private Value varName;
	private Expression expression;

	public Attrib(Value varName, Expression expression) {
		this.varName = varName;
		this.expression = expression;
	}

	public void execute() {
		System.out.println(varName.evaluate() + " = " + expression.evaluate()
				+ ";");
	}
}
