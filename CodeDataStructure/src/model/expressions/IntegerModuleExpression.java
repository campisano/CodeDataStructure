package model.expressions;

import model.I_Expression;

public class IntegerModuleExpression extends
		A_BinaryExpression<Integer, Integer> {

	public IntegerModuleExpression(I_Expression<Integer> _first,
			I_Expression<Integer> _second) {
		super(_first, _second);
	}

	public Integer evaluate() {
		return new Integer(m_first.evaluate() % (m_second.evaluate()));
	}
}
