package model.expression;

public class Value implements Expression {
	private String varName;

	public Value(String varName) {
		this.varName = varName;
	}

	public String evaluate() {
		return varName;
	}
}
