package visitor;

import java.util.List;

import model.statement.Statement;

public interface DefineScopeVisitor extends NodeVisitor {

	void execute(String scopeName, List<Statement> statements);

}
