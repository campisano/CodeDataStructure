package model.statements;

import model.common.Scope;

import model.expressions.I_Expression;

public class PrintStatement<T> implements I_Statement {
	private I_Expression<T> m_exp;

	public PrintStatement(I_Expression<T> _exp) {
		m_exp = _exp;
	}

	public void execute(Scope _scope) throws Exception {
		System.out.println(m_exp.evaluate(_scope));
	}
}
