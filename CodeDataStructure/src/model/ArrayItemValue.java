package model;

import model.expressions.VarExpression;

public class ArrayItemValue<T> extends VarExpression<T> {
	private I_Expression<T[]> m_collection;
	private I_Expression<Integer> m_index;

	public ArrayItemValue(I_Expression<T[]> _collection,
			I_Expression<Integer> _index) {
		super();
		m_collection = _collection;
		m_index = _index;
	}

	public T evaluate() {
		return m_collection.evaluate()[m_index.evaluate()];
	}

	public void assign(T _value) {
		m_collection.evaluate()[m_index.evaluate()] = _value;
	}
}
