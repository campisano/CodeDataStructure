package visitor;

import java.util.List;

import model.expression.Expression;
import model.statement.Statement;

public interface WhileVisitor extends NodeVisitor {
	void execute(Expression condition, List<Statement> statements);
}
