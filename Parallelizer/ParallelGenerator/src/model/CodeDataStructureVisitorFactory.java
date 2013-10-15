package model;

import visitor.ArrayIntVisitor;
import visitor.AttribVisitor;
import visitor.DoTimesVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.WhileVisitor;

public interface CodeDataStructureVisitorFactory {
	IntVarVisitor getIntVar();

	ArrayIntVisitor getArrayInt();

	WhileVisitor getWhile();

	DoTimesVisitor getDoTimes();

	PrintVisitor getPrint();

	AttribVisitor getAttrib();
}
