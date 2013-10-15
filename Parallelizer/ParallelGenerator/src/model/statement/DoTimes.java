package model.statement;

import model.Scope;
import model.VisitableNode;
import model.expression.Expression;
import visitor.DoTimesVisitor;

public class DoTimes extends VisitableNode implements Statement {
	private Scope scope;
	private Statement statement;
	private Expression times;

	public DoTimes(Scope scope, Statement statement, Expression times) {
		this.scope = scope;

		this.statement = statement;
		this.times = times;
	}

	public void execute() {
		((DoTimesVisitor) visitor).execute(scope, statement, times);
	}
}
