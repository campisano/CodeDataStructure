package model.statements;

import model.common.Scope;
import model.expressions.I_ConditionalExpression;

public class WhileStatement implements I_Statement {
	private I_ConditionalExpression m_condition;
	private I_Statement m_statement;

	public WhileStatement(I_ConditionalExpression _condition,
			I_Statement _statement) {
		m_condition = _condition;
		m_statement = _statement;
	}

	public void execute(Scope _scope) throws Exception {
		while (m_condition.evaluate(_scope)) {
			m_statement.execute(_scope);
		}
	}
}