package model.expressions.condition;

import model.common.Scope;
import model.expressions.A_BinaryExpression;
import model.expressions.I_ConditionalExpression;
import model.expressions.I_Expression;

public class DifferentThenCondition<T extends Comparable<T>> extends
		A_BinaryExpression<T, Boolean> implements I_ConditionalExpression {

	public DifferentThenCondition(I_Expression<T> _first,
			I_Expression<T> _second) {
		super(_first, _second);
	}

	public Boolean evaluate(Scope _scope) throws Exception {
		return m_first.evaluate(_scope).compareTo(m_second.evaluate(_scope)) != 0;
	}
}
