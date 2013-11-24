package model.expressions;

import model.common.Scope;

public class VarExpression<T> implements I_Expression<T> {
	private String m_name;

	public VarExpression(String _name) {
		m_name = _name;
	}

	public T evaluate(Scope _scope) {
		return _scope.<T> get(m_name);
	}

	public String getName() {
		return m_name;
	}
}
