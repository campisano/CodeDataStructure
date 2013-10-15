package visitor;

public interface ArrayIntVisitor extends NodeVisitor {

	void execute(String name, int size);

}
