package model.expressions;

import model.common.Scope;

public class LongAddExpression extends A_BinaryExpression<Long, Long> {
	public LongAddExpression(I_Expression<Long> _first,
			I_Expression<Long> _second) {
		super(_first, _second);
	}

	public Long evaluate(Scope _scope) throws Exception {
		return m_first.evaluate(_scope) + m_second.evaluate(_scope);
	}
}
