package simple_model.expression;

public abstract class BinaryExpression implements Expression {
	protected Expression e1;
	protected Expression e2;

	public BinaryExpression(String e1, String e2) {
		this.e1 = new Value(e1);
		this.e2 = new Value(e2);
	}

	public BinaryExpression(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
}
