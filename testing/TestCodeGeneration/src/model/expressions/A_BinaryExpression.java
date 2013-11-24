package model.expressions;

public abstract class A_BinaryExpression<T, R> implements I_Expression<R> {
	protected I_Expression<T> m_first;
	protected I_Expression<T> m_second;

	public A_BinaryExpression(I_Expression<T> _first, I_Expression<T> _second) {
		m_first = _first;
		m_second = _second;
	}
}
