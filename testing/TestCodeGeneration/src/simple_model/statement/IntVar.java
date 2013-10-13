package simple_model.statement;

import simple_model.VisitableNode;
import visitor.IntVarVisitor;

public class IntVar extends VisitableNode implements Statement {
	private String name;

	public IntVar(String name) {
		this.name = name;
	}

	public void execute() {
		((IntVarVisitor) visitor).execute(name);
	}
}
