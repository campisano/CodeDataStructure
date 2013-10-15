package model.statement;

import model.VisitableNode;
import visitor.ArrayIntVisitor;

public class ArrayInt extends VisitableNode implements Statement {
	private String name;
	private int size;

	public ArrayInt(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public void execute() {
		((ArrayIntVisitor) visitor).execute(name, size);
	}
}
