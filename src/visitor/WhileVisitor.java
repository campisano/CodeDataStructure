package visitor;

import java.util.List;

import simple_model.expression.Expression;
import simple_model.statement.Statement;

public interface WhileVisitor extends NodeVisitor {
	void execute(Expression condition, List<Statement> statements);
}
