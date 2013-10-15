package model.statement;

import java.util.ArrayList;
import java.util.List;

import visitor.DefineScopeVisitor;
import model.VisitableNode;

public class DefineScope extends VisitableNode implements Statement {
	private String scopeName;
	private List<Statement> statements;

	public DefineScope(String scopeName, Statement... statements) {
		this.scopeName = scopeName;
		this.statements = new ArrayList<Statement>();

		for (Statement e : statements) {
			this.statements.add(e);
		}
	}

	public void execute() {
		((DefineScopeVisitor) visitor).execute(scopeName, statements);
	}
}
