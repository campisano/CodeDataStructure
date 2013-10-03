package simple_model.expression;

public class LessThen extends BinaryExpression {
	public LessThen(Expression e1, Expression e2) {
		super(e1, e2);
	}

	public String evaluate() {
		return e1.evaluate() + "<" + e2.evaluate();
	}
}
