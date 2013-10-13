package model.expressions;

import model.I_Expression;

public class ConstExpression<T> implements I_Expression<T> {
	protected T m_value;

	public ConstExpression(T _value) {
		m_value = _value;
	}

	public T evaluate() {
		return m_value;
	}
}
