package visitor;

import model.expression.Expression;

public interface PrintVisitor extends NodeVisitor {
	void execute(Expression expression);
}
