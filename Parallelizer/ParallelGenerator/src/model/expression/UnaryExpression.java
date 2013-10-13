package model.expression;

public abstract class UnaryExpression implements Expression {
	protected Expression e;

	public UnaryExpression(String e) {
		this.e = new Value(e);
	}

	public UnaryExpression(Expression e) {
		this.e = e;
	}
}
