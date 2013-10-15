package java_visitor;

//import java.util.List;

import model.expression.Expression;
import model.expression.Value;
import model.statement.Statement;
import visitor.DoTimesVisitor;

public class JavaDoTimesVisitor implements DoTimesVisitor {
	public void execute(Value scope, Statement statement, Expression times) {
		System.out.println("for (int time = 0; time < " + times.evaluate()
				+ "; ++time) {");

		// for (Statement s : statements) {
		// System.out.print("    ");
		// s.execute();
		// }
		statement.execute();

		System.out.println("}");
	}
}
