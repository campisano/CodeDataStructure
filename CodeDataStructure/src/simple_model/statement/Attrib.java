package simple_model.statement;

import simple_model.VisitableNode;
import simple_model.expression.Expression;
import simple_model.expression.Value;
import visitor.AttribVisitor;

public class Attrib extends VisitableNode implements Statement {
	private Value varName;
	private Expression expression;

	public Attrib(Value varName, Expression expression) {
		this.varName = varName;
		this.expression = expression;
	}

	public void execute() {
		((AttribVisitor) visitor).execute(varName, expression);
	}
}
