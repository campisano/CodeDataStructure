package model;

import java.util.ArrayList;
import java.util.List;

import model.expression.Expression;

public class Scope {
	private List<Expression> expressions;

	public Scope(Expression... expressions) {

		this.expressions = new ArrayList<Expression>();

		for (Expression e : expressions) {
			this.expressions.add(e);
		}
	}
}
