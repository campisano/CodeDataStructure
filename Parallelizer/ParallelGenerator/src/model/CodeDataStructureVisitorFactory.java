package model;

import visitor.AttribVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.WhileVisitor;

public interface CodeDataStructureVisitorFactory {
	IntVarVisitor getIntVar();
	WhileVisitor getWhile();
	PrintVisitor getPrint();
	AttribVisitor getAttrib();
}
