package model.statements;

import model.I_Statement;
import model.expressions.I_ConditionalExpression;

public class WhileStatement implements I_Statement {
	private I_ConditionalExpression m_condition;
	private I_Statement m_statement;

	public WhileStatement(I_ConditionalExpression _condition, I_Statement _statement) {
		m_condition = _condition;
		m_statement = _statement;
	}

	public void execute() {
		while (m_condition.evaluate()) {
			m_statement.execute();
		}
	}
}