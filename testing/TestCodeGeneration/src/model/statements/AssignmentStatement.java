package model.statements;

import model.common.Scope;
import model.expressions.I_Expression;
import model.expressions.VarExpression;

public class AssignmentStatement<T> implements I_Statement {
	private VarExpression<T> m_lvalue;
	private I_Expression<T> m_rvalue;

	public AssignmentStatement(VarExpression<T> _lvalue, I_Expression<T> _rvalue) {
		m_lvalue = _lvalue;
		m_rvalue = _rvalue;
	}

	public void execute(Scope _scope) throws Exception {
		_scope.assign(m_lvalue.getName(), m_rvalue.evaluate(_scope));
	}
}
