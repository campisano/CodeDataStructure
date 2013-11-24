package model.statements;

import model.common.Scope;

import model.expressions.I_ConditionalExpression;

public class IfStatement implements I_Statement {
	private I_ConditionalExpression m_condition;
	private I_Statement m_statement_true;
	private I_Statement m_statement_false;

	public IfStatement(I_ConditionalExpression _condition,
			I_Statement _statement_true, I_Statement _statement_false) {
		m_condition = _condition;
		m_statement_true = _statement_true;
		m_statement_false = _statement_false;
	}

	public IfStatement(I_ConditionalExpression _condition,
			I_Statement _statement_true) {
		this(_condition, _statement_true, null);
	}

	public void execute(Scope _scope) throws Exception {
		if (m_condition.evaluate(_scope)) {
			m_statement_true.execute(_scope);
		} else if (m_statement_false != null) {
			m_statement_false.execute(_scope);
		}
	}
}
