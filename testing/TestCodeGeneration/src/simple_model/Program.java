package simple_model;

import simple_model.statement.Statement;

public class Program {
	public Program(Statement... statements) {
		for (Statement statement : statements) {
			statement.execute();
		}
	}
}
