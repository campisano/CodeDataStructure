package test;

import java.util.ArrayList;
import java.util.List;

public class SimpleLoopTest {

	public interface STATEMENT {
		public void execute();
	}

	public interface EXPRESSION {
		public String evaluate();
	}

	public abstract class UNARYEXPRESSION implements EXPRESSION {
		protected EXPRESSION e;

		public UNARYEXPRESSION(String e) {
			this.e = new VALUE(e);
		}

		public UNARYEXPRESSION(EXPRESSION e) {
			this.e = e;
		}
	}

	public abstract class BINARYEXPRESSION implements EXPRESSION {
		protected EXPRESSION e1;
		protected EXPRESSION e2;

		public BINARYEXPRESSION(String e1, String e2) {
			this.e1 = new VALUE(e1);
			this.e2 = new VALUE(e2);
		}

		public BINARYEXPRESSION(EXPRESSION e1, EXPRESSION e2) {
			this.e1 = e1;
			this.e2 = e2;
		}
	}

	public class PROGRAM {
		public PROGRAM(STATEMENT... statements) {
			for (STATEMENT statement : statements) {
				statement.execute();
			}
		}
	}

	public class INTVAR implements STATEMENT {
		private String name;

		public INTVAR(String name) {
			this.name = name;
		}

		public void execute() {
			System.out.println("int " + name + ";");
		}
	}

	public class WHILE implements STATEMENT {
		private EXPRESSION condition;
		private List<STATEMENT> statements;

		public WHILE(EXPRESSION condition, STATEMENT... statements) {
			this.condition = condition;
			this.statements = new ArrayList<STATEMENT>();

			for (STATEMENT s : statements) {
				this.statements.add(s);
			}
		}

		public void execute() {
			System.out.println("while (" + condition.evaluate() + ") {");

			for (STATEMENT s : statements) {
				System.out.print("    ");
				s.execute();
			}

			System.out.println("}");
		}
	}

	public class LT extends BINARYEXPRESSION {
		public LT(EXPRESSION e1, EXPRESSION e2) {
			super(e1, e2);
		}

		public String evaluate() {
			return e1.evaluate() + "<" + e2.evaluate();
		}
	}

	public class ADD extends BINARYEXPRESSION {
		public ADD(EXPRESSION e1, EXPRESSION e2) {
			super(e1, e2);
		}

		public String evaluate() {
			return e1.evaluate() + "+" + e2.evaluate();
		}
	}

	public class VALUE implements EXPRESSION {
		private String varName;

		public VALUE(String varName) {
			this.varName = varName;
		}

		public String evaluate() {
			return varName;
		}
	}

	public class ATRIB implements STATEMENT {
		private VALUE varName;
		private EXPRESSION expression;

		public ATRIB(VALUE varName, EXPRESSION expression) {
			this.varName = varName;
			this.expression = expression;
		}

		public void execute() {
			System.out.println(varName.evaluate() + " = "
					+ expression.evaluate() + ";");
		}
	}

	public class PRINT implements STATEMENT {

		private EXPRESSION expression;

		public PRINT(EXPRESSION expression) {
			this.expression = expression;
		}

		public void execute() {
			System.out.println("std::cout << " + expression.evaluate() + ";");
		}
	}

	public class CONST extends UNARYEXPRESSION {

		public CONST(int value) {
			super(String.valueOf(value));
		}

		public String evaluate() {
			return e.evaluate();
		}
	}

	public SimpleLoopTest() {
		new PROGRAM(
				new INTVAR("I"),
				new INTVAR("J"),
				new WHILE(
						new LT(
								new VALUE("I"),
								new CONST(10)
						),
						new ATRIB(
								new VALUE("J"),
								new ADD(
										new VALUE("J"),
										new CONST(10)
								)
						),
						new ATRIB(
								new VALUE("I"),
								new ADD(
										new VALUE("I"),
										new CONST(1)
								)
						)
				),
				new PRINT(
						new VALUE("I")
				),
				new PRINT(
						new VALUE("J")
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