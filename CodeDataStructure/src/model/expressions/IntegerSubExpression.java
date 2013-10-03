package model.expressions;

import model.I_Expression;

public class IntegerSubExpression extends A_BinaryExpression<Integer, Integer> {
	public IntegerSubExpression(I_Expression<Integer> _first,
			I_Expression<Integer> _second) {
		super(_first, _second);
	}

	public Integer evaluate() {
		return m_first.evaluate() - m_second.evaluate();
	}
}
