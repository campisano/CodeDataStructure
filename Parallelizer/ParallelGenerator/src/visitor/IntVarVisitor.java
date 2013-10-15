package visitor;

public interface IntVarVisitor extends NodeVisitor {

	void execute(String name, Integer value, boolean constant);

}
