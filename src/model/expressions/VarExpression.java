package model.expressions;

import model.I_Expression;

public class VarExpression<T> implements I_Expression<T> {
	protected T m_value;

	public VarExpression() {
	}
	
	//TODO [CMP] verificar um jeito melhor de fazer isso
	public void internal_assign(T _value) {
		m_value = _value;
	}

	public T evaluate() {
		return m_value;
	}
}
