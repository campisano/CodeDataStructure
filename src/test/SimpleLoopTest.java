package test;

import simple_model.Program;
import simple_model.expression.Add;
import simple_model.expression.Const;
import simple_model.expression.Expression;
import simple_model.expression.LessThen;
import simple_model.expression.Value;
import simple_model.statement.Attrib;
import simple_model.statement.IntVar;
import simple_model.statement.Print;
import simple_model.statement.Statement;
import simple_model.statement.While;

public class SimpleLoopTest {

	public static Program PROGRAM(Statement... statements) {
		return new Program(statements);
	}

	public static IntVar INTVAR(String varName) {
		return new IntVar(varName);
	}

	public static While WHILE(Expression condition, Statement... statements) {
		return new While(condition, statements);
	}

	public static Print PRINT(Expression expression) {
		return new Print(expression);
	}

	public static Value VALUE(String varName) {
		return new Value(varName);
	}

	public static Const CONST(int value) {
		return new Const(value);
	}

	public static Attrib ATTRIB(Value varName, Expression expression) {
		return new Attrib(varName, expression);
	}

	public static Add ADD(Expression e1, Expression e2) {
		return new Add(e1, e2);
	}

	public static LessThen LT(Expression e1, Expression e2) {
		return new LessThen(e1, e2);
	}

	public SimpleLoopTest() {
		PROGRAM(
				INTVAR("I"),
				INTVAR("J"),
				WHILE(
						LT(
								VALUE("I"),
								CONST(10)
						),
						ATTRIB(
								VALUE("J"),
								ADD(
										VALUE("J"),
										CONST(10)
								)
						),
						ATTRIB(
								VALUE("I"),
								ADD(
										VALUE("I"),
										CONST(1)
								)
						)
				),
				PRINT(
						VALUE("I")
				),
				PRINT(
						VALUE("J")
				)
		);
	}

	public static void main(String[] args) {

		new SimpleLoopTest();

		// ouptut
		// int I;
		// int J;
		// while (I<10) {
		//     J = J+10;
		//     I = I+1;
		// }
		// std::cout << I;
		// std::cout << J;
	}
}