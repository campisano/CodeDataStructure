package visitor_imp.java_visitor;

import model.expression.Expression;
import model.expression.Value;
import visitor.AttribVisitor;

public class JavaAttribVisitor implements AttribVisitor {
	public void execute(Value varName, Expression expression) {
		System.out.println(varName.evaluate() + " = " + expression.evaluate()
				+ ";");
	}
}
