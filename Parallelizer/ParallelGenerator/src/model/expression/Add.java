package model.expression;

public class Add extends BinaryExpression {
	public Add(Expression e1, Expression e2) {
		super(e1, e2);
	}

	public String evaluate() {
		return e1.evaluate() + "+" + e2.evaluate();
	}
}
