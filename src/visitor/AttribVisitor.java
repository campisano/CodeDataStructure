package visitor;

import simple_model.expression.Expression;
import simple_model.expression.Value;

public interface AttribVisitor extends NodeVisitor {
	void execute(Value varName, Expression expression);
}
