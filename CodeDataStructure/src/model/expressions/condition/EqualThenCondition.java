package model.expressions.condition;

import model.I_Expression;
import model.expressions.A_BinaryExpression;
import model.expressions.I_ConditionalExpression;

public class EqualThenCondition<T extends Comparable<T>> extends
		A_BinaryExpression<T, Boolean> implements I_ConditionalExpression {

	public EqualThenCondition(I_Expression<T> _first, I_Expression<T> _second) {
		super(_first, _second);
	}

	public Boolean evaluate() {
		return m_first.evaluate().compareTo(m_second.evaluate()) == 0;
	}
}
