package model.expressions;

import model.I_Expression;
import model.I_Statement;

public abstract class A_Function<T, R> implements I_Expression<R> {
	protected T m_parameters;
	protected I_Statement m_statement;
	protected VarExpression<R> m_result;

	public A_Function(T _parameters, I_Statement _statement) {
		m_parameters = _parameters;
		m_statement = _statement;
	}
}
