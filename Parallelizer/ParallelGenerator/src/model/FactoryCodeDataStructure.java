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
import model.statement.DefineScope;
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

	public Program PROGRAM(Definition definition, Statement... statements) {
		return new Program(definition, statements);
	}

	public Definition DEFINITION(Statement... statements) {
		return new Definition(statements);
	}

	public IntVar INTVAR(String varName) {
		IntVar intVar = new IntVar(varName);
		intVar.assign(visitor.getIntVar());

		return intVar;
	}

	public ArrayInt ARRAYINT(String arrName, int size) {
		ArrayInt arrayInt = new ArrayInt(arrName, size);
		arrayInt.assign(visitor.getArrayInt());

		return arrayInt;
	}

	public DefineScope DEFINESCOPE(String scopeName, Statement... statements) {
		DefineScope defineScope = new DefineScope(scopeName, statements);
		defineScope.assign(visitor.getDefineScope());

		return defineScope;
	}

	public While WHILE(Expression condition, Statement... statements) {
		While ourWhile = new While(condition, statements);
		ourWhile.assign(visitor.getWhile());

		return ourWhile;
	}

	public DoTimes DOTIMES(Value scope, Statement statement, Expression times) {
		DoTimes doTimes = new DoTimes(scope, statement, times);
		doTimes.assign(visitor.getDoTimes());

		return doTimes;
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

	// TODO [CMP] é um statement ou uma expression? por enquanto uma
	// expression...
	public ArrayItem ARRAYITEM(/* TODO ArrayInt ? */String arrName,
	/* TODO Value ? */String itemName) {
		ArrayItem arrItem = new ArrayItem(arrName, itemName);
		// arrItem.assign(visitor.getArrayItem());

		return arrItem;
	}

	public Const CONST(int value) {
		Const ourConst = new Const(value);
		// ourConst.assign(visitor.getConst());

		return ourConst;
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
