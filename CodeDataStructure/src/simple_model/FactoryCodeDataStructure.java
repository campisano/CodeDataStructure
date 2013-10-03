package simple_model;

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

public class FactoryCodeDataStructure {

	private CodeDataStructureVisitorFactory visitor;

	public FactoryCodeDataStructure(CodeDataStructureVisitorFactory visitor) {
		this.visitor = visitor;
	}

	public Program PROGRAM(Statement... statements) {
		return new Program(statements);
	}

	public IntVar INTVAR(String varName) {
		IntVar intvar = new IntVar(varName);
		intvar.assign(visitor.getIntVar());

		return intvar;
	}

	public While WHILE(Expression condition, Statement... statements) {
		While while2 = new While(condition, statements);
		while2.assign(visitor.getWhile());

		return while2;
	}

	public Print PRINT(Expression expression) {
		Print print = new Print(expression);
		print.assign(visitor.getPrint());

		return print;
	}

	public Value VALUE(String varName) {
		Value value = new Value(varName);
		// value.assign(visitor.getValue());

		return value;
	}

	public Const CONST(int value) {
		Const const2 = new Const(value);
		// const2.assign(visitor.getConst());

		return const2;
	}

	public Attrib ATTRIB(Value varName, Expression expression) {
		Attrib attrib = new Attrib(varName, expression);
		attrib.assign(visitor.getAttrib());

		return attrib;
	}

	public Add ADD(Expression e1, Expression e2) {
		Add add = new Add(e1, e2);
		// add.assign(visitor.getAdd());

		return add;
	}

	public LessThen LT(Expression e1, Expression e2) {
		LessThen lt = new LessThen(e1, e2);
		// lt.assign(visitor.getLessThen());

		return lt;
	}
}
