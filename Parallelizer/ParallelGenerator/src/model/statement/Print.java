package model.statement;

import model.VisitableNode;
import model.expression.Expression;
import visitor.PrintVisitor;

public class Print extends VisitableNode implements Statement {

	private Expression expression;

	public Print(Expression expression) {
		this.expression = expression;
	}

	public void execute() {
		((PrintVisitor) visitor).execute(expression);
	}
}
