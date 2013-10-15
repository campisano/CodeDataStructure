package visitor;

import model.Scope;
import model.expression.Expression;
import model.statement.Statement;

public interface DoTimesVisitor extends NodeVisitor {
	void execute(Scope scope, Statement statement, Expression times);
}
