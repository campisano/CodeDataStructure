package model;

import model.statement.Statement;

public class Program {
	public Program(Statement... statements) {
		for (Statement statement : statements) {
			statement.execute();
		}
	}
}
