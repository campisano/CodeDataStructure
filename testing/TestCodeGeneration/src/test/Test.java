package test;

import model.Program;
import model.common.Scope;

public class Test extends Program {

	public void testDeclaration() throws Exception {
		int result;
		
		Scope scope = PROGRAM(
				INTEGER("result"),
				PRINT(VAR("result"))
				);
		
		result = VAR("result").evaluate(scope);
		
		if (result != 0) {
			throw new Exception("testDeclaration() failed: " + result + " != " + 0);
		}
	}

	public void testAssignment() throws Exception {
		int value = 123;
		int result;

		Scope scope = PROGRAM(
				INTEGER("VALUE"),
				ASSIGN(VAR("VALUE"), CONST(value)),
				PRINT(VAR("VALUE"))
				);
		
		result = VAR("VALUE").evaluate(scope);
		
		if (result != value) {
			throw new Exception("testAssignment() failed: " + result + " != " + value);
		}
	}

	public void testAdd() throws Exception {
		int left = 1, right = 2;
		int result;

		Scope scope = PROGRAM(
				INTEGER("left"),
				ASSIGN(VAR("left"), CONST(left)),
				INTEGER("right"),
				ASSIGN(VAR("right"), CONST(right)),
				INTEGER("result"),
				ASSIGN(
						VAR("result"),
						ADD(VAR("left"), VAR("right"))
				),
				PRINT(VAR("result"))
				);
		
		result = VAR("result").evaluate(scope);

		if (result != left + right) {
			throw new Exception("testAdd() failed!: " + result + " != " + left + " + " + right);
		}
	}

	public void testSub() throws Exception {
		int left = 2, right = 1;
		int result;

		Scope scope = PROGRAM(
				INTEGER("left"),
				ASSIGN(VAR("left"), CONST(left)),
				INTEGER("right"),
				ASSIGN(VAR("right"), CONST(right)),
				INTEGER("result"),
				ASSIGN(
						VAR("result"),
						SUB(VAR("left"), VAR("right"))
				),
				PRINT(VAR("result"))
				);

		result = VAR("result").evaluate(scope);

		if (result != left - right) {
			throw new Exception("testSub() failed: " + result + " != " + left + " - " + right);
		}
	}
	
	public void testWhileSum() throws Exception {
		int repeat = 100, increment = 10;
		int result;
		
		Scope scope = PROGRAM(
				INTEGER("i"),
				ASSIGN(VAR("i"), CONST(0)),
				INTEGER("result"),
				ASSIGN(VAR("result"), CONST(0)),
				WHILE(
						LT(VAR("i"), CONST(repeat)),
						ASSIGN(
								VAR("result"),
								ADD(VAR("result"), CONST(increment))
						),
						ASSIGN(
								VAR("i"),
								ADD(VAR("i"), CONST(1))
						)
				),
				PRINT(VAR("result"))
				);

		result = VAR("result").evaluate(scope);

		if (result != repeat * increment) {
			throw new Exception("testWhileSum() failed: " + result + " != " + repeat + " * " + increment);
		}
	}
	
	public void testFunctionMod() throws Exception {
		int dividend = 200, divisor = 7;
		int result;
		
		Scope scope = PROGRAM(
				INTEGER("dividend"),
				ASSIGN(VAR("dividend"), CONST(dividend)),
				INTEGER("divisor"),
				ASSIGN(VAR("divisor"), CONST(divisor)),
				INTEGER("result"),
				ASSIGN(VAR("result"), CONST(0)),
				FUNCTION(
						ASSIGN(
								VAR("result"),
								MOD(VAR("dividend"), VAR("divisor"))
								),
						VAR("dividend"),
						VAR("divisor")
						),
				PRINT(VAR("result"))
				);

		result = VAR("result").evaluate(scope);

		if (result != dividend % divisor) {
			throw new Exception("testFunctionMod() failed: " + result + " != "  + dividend + " % " + divisor);
		}
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		
		try
		{
			test.testDeclaration();
			test.testAssignment();
			test.testAdd();
			test.testSub();
			test.testWhileSum();
			test.testFunctionMod();
		
			System.out.print("Tests passed.");
		} catch (Exception _exc) {
			_exc.printStackTrace();
			System.out.print("Tests failed!");
		}
	}
}
