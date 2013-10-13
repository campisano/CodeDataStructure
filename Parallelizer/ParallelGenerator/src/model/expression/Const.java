package model.expression;

public class Const extends UnaryExpression {

	public Const(int value) {
		super(String.valueOf(value));
	}

	public String evaluate() {
		return e.evaluate();
	}
}
