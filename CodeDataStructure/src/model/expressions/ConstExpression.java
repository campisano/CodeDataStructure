package model.expressions;

import model.common.Scope;

public class ConstExpression<T> implements I_Expression<T> {
	protected T m_value;

	public ConstExpression(T _value) {
		m_value = _value;
	}

	public T evaluate(Scope _scope) {
		return m_value;
	}
}
