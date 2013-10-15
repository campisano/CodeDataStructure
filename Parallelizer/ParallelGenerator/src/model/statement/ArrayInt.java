package model.statement;

import model.VisitableNode;
import model.expression.Value;
import visitor.ArrayIntVisitor;

public class ArrayInt extends VisitableNode implements Statement {
	private String name;
	private Value size;

	public ArrayInt(String name, Value size) {
		this.name = name;
		this.size = size;
	}

	public void execute() {
		((ArrayIntVisitor) visitor).execute(name, size);
	}
}
