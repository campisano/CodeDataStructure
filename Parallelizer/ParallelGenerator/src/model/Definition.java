package model;

import java.util.ArrayList;
import java.util.List;

import model.statement.Statement;

public class Definition {
	List<Statement> statements;

	public Definition(Statement... statements) {
		this.statements = new ArrayList<Statement>();

		for (Statement statement : statements) {
			this.statements.add(statement);
		}
	}

	public void execute() {
		for (Statement statement : statements) {
			statement.execute();
		}
	}
}
