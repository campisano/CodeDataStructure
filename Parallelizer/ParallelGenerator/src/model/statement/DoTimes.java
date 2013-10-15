package model.statement;

import model.VisitableNode;
import model.expression.Expression;
import model.expression.Value;
import visitor.DoTimesVisitor;

public class DoTimes extends VisitableNode implements Statement {
	private Value scope;
	private Statement statement;
	private Expression times;

	public DoTimes(Value scope, Statement statement, Expression times) {
		this.scope = scope;

		this.statement = statement;
		this.times = times;
	}

	public void execute() {
		((DoTimesVisitor) visitor).execute(scope, statement, times);
	}
}
