package visitor;

import simple_model.expression.Expression;

public interface PrintVisitor extends NodeVisitor {
	void execute(Expression expression);
}
