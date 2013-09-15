package java_visitor;

import simple_model.expression.Expression;
import simple_model.expression.Value;
import visitor.AttribVisitor;

public class JavaAttribVisitor implements AttribVisitor {
	public void execute(Value varName, Expression expression) {
		System.out.println(varName.evaluate() + " = " + expression.evaluate()
				+ ";");
	}
}
