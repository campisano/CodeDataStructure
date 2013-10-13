package model.expressions;

import model.I_Expression;

public class LongAddExpression extends A_BinaryExpression<Long, Long> {
	public LongAddExpression(I_Expression<Long> _first,
			I_Expression<Long> _second) {
		super(_first, _second);
	}

	public Long evaluate() {
		return m_first.evaluate() + m_second.evaluate();
	}
}
