package visitor;

import model.expression.Value;

public interface ArrayIntVisitor extends NodeVisitor {

	void execute(String name, Value size);

}
