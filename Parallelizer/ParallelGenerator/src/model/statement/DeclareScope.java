package model.statement;

import model.VisitableNode;
import visitor.DeclareScopeVisitor;

public class DeclareScope extends VisitableNode implements Statement {
	private String scopeName;
	private String valueName;

	public DeclareScope(String scopeName, String valueName) {
		this.scopeName = scopeName;
		this.valueName = valueName;
	}

	public void execute() {
		((DeclareScopeVisitor) visitor).execute(scopeName, valueName);
	}
}
