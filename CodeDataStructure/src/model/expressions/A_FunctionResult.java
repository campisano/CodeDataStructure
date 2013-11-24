package model.expressions;

import model.common.Language;
import model.statements.I_Statement;

public abstract class A_FunctionResult<T, R> extends Language implements
		I_Expression<R> {
	protected T m_parameters;
	protected I_Statement m_statement;

	public A_FunctionResult(T _parameters, I_Statement _statement) {
		m_parameters = _parameters;
		m_statement = _statement;
	}
}
