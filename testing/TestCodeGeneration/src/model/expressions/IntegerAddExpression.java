package model.expressions;

import model.I_Expression;

public class IntegerAddExpression extends A_BinaryExpression<Integer, Integer> {
	public IntegerAddExpression(I_Expression<Integer> _first,
			I_Expression<Integer> _second) {
		super(_first, _second);
	}

	public Integer evaluate() {
		return m_first.evaluate() + m_second.evaluate();
	}
}
