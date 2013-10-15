package model;

import model.expression.Add;
import model.expression.ArrayItem;
import model.expression.Const;
import model.expression.Expression;
import model.expression.LessThen;
import model.expression.Value;
import model.statement.ArrayInt;
import model.statement.Attrib;
import model.statement.DoTimes;
import model.statement.IntVar;
import model.statement.Print;
import model.statement.Statement;
import model.statement.While;

public class FactoryCodeDataStructure {

	private CodeDataStructureVisitorFactory visitor;

	public FactoryCodeDataStructure(CodeDataStructureVisitorFactory visitor) {
		this.visitor = visitor;
	}

	public Program PROGRAM(Statement... statements) {
		return new Program(statements);
	}

	public IntVar INTVAR(String varName) {
		IntVar int_var = new IntVar(varName);
		int_var.assign(visitor.getIntVar());

		return int_var;
	}

	public ArrayInt ARRAYINT(String arrName, int size) {
		ArrayInt array_int = new ArrayInt(arrName, size);
		array_int.assign(visitor.getArrayInt());

		return array_int;
	}

	public While WHILE(Expression condition, Statement... statements) {
		While our_while = new While(condition, statements);
		our_while.assign(visitor.getWhile());

		return our_while;
	}

	public DoTimes DOTIMES(Scope scope, Statement statement, Expression times) {
		DoTimes do_times = new DoTimes(scope, statement, times);
		do_times.assign(visitor.getDoTimes());

		return do_times;
	}

	public Scope SCOPE(Expression... expressions) {
		Scope scope = new Scope(expressions);
		// scope.assign(visitor.getScope());

		return scope;
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

	//TODO [CMP] é um statement ou uma expression? por enquanto uma expression...
	public ArrayItem ARRAYITEM(/* TODO ArrayInt ? */String arrName,
	/* TODO Value ? */String itemName) {
		ArrayItem arrItem = new ArrayItem(arrName, itemName);
		//arrItem.assign(visitor.getArrayItem());

		return arrItem;
	}

	public Const CONST(int value) {
		Const our_const = new Const(value);
		// our_const.assign(visitor.getConst());

		return our_const;
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
