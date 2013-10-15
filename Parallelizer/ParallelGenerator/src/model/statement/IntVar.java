package model.statement;

import model.VisitableNode;
import visitor.IntVarVisitor;

public class IntVar extends VisitableNode implements Statement {
	private String name;
	private Integer value;
	private boolean constant;

	public IntVar(String name) {
		this.name = name;
		this.value = null;
		this.constant = false;
	}

	public IntVar(String name, int value) {
		this.name = name;
		this.value = new Integer(value);
		this.constant = false;
	}

	public IntVar(String name, int value, boolean constant) {
		this.name = name;
		this.value = new Integer(value);
		this.constant = constant;
	}

	public void execute() {
		((IntVarVisitor) visitor).execute(name, value, constant);
	}
}
