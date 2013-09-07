package model.statements;

import model.I_Expression;
import model.I_Statement;
import model.expressions.VarExpression;

public class AssignmentStatement<T> implements I_Statement {
	private VarExpression<T> m_lvalue;
	private I_Expression<T> m_rvalue;

	public AssignmentStatement(VarExpression<T> _lvalue, I_Expression<T> _rvalue) {
		m_lvalue = _lvalue;
		m_rvalue = _rvalue;
	}

	public void execute() {
		m_lvalue.internal_assign(m_rvalue.evaluate());
	}
}
