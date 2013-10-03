package model.statements;

import model.I_Statement;
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

	public void execute() {
		if (m_condition.evaluate()) {
			m_statement_true.execute();
		} else if (m_statement_false != null) {
			m_statement_false.execute();
		}
	}
}
