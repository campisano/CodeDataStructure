package visitor;

public interface DeclareScopeVisitor extends NodeVisitor {

	void execute(String scopeName, String valueName);

}
