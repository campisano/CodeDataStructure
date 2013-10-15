package model;

import model.statement.Statement;

public class Program {
	public Program(Statement... statements) {
		for (Statement statement : statements) {
			statement.execute();
		}
	}

	public Program(Definition definition, Statement... statements) {
		definition.execute();

		for (Statement statement : statements) {
			statement.execute();
		}
	}
}
