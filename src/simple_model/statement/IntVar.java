package simple_model.statement;

public class IntVar implements Statement {
	private String name;

	public IntVar(String name) {
		this.name = name;
	}

	public void execute() {
		System.out.println("int " + name + ";");
	}
}
