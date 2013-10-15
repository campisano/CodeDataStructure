package visitor;

import model.expression.Expression;
import model.expression.Value;
import model.statement.Statement;

public interface DoTimesVisitor extends NodeVisitor {
	void execute(Value scope, Statement statement, Expression times);
}
