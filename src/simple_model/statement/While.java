package simple_model.statement;

import java.util.ArrayList;
import java.util.List;

import simple_model.VisitableNode;
import simple_model.expression.Expression;
import visitor.WhileVisitor;

public class While extends VisitableNode implements Statement {
	private Expression condition;
	private List<Statement> statements;

	public While(Expression condition, Statement... statements) {
		this.condition = condition;
		this.statements = new ArrayList<Statement>();

		for (Statement s : statements) {
			this.statements.add(s);
		}
	}

	public void execute() {
		((WhileVisitor) visitor).execute(condition, statements);
	}
}
