package model.statements;

import model.common.Scope;

public class IntegerStatement implements I_Statement {

	private String m_name;

	public IntegerStatement(String _name) {
		m_name = _name;
	}

	public void execute(Scope _scope) throws Exception {
		_scope.declare(m_name, new Integer(0));
	}
}
