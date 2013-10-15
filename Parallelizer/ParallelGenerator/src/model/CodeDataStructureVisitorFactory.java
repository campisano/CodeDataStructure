package model;

import visitor.ArrayIntVisitor;
import visitor.AttribVisitor;
import visitor.DeclareScopeVisitor;
import visitor.DoTimesVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.DefineScopeVisitor;
import visitor.WhileVisitor;

public interface CodeDataStructureVisitorFactory {
	DefineScopeVisitor getDefineScope();

	DeclareScopeVisitor getDeclareScope();

	IntVarVisitor getIntVar();

	ArrayIntVisitor getArrayInt();

	WhileVisitor getWhile();

	DoTimesVisitor getDoTimes();

	PrintVisitor getPrint();

	AttribVisitor getAttrib();
}
