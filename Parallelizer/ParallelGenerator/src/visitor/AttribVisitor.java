package visitor;

import model.expression.Expression;
import model.expression.Value;

public interface AttribVisitor extends NodeVisitor {
	void execute(Value varName, Expression expression);
}
