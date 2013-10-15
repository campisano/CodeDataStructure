package java_visitor;

import model.CodeDataStructureVisitorFactory;
import visitor.ArrayIntVisitor;
import visitor.AttribVisitor;
import visitor.DoTimesVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.WhileVisitor;

public class JavaGeneratorCodeDataStructureVisitorFactory implements
		CodeDataStructureVisitorFactory {

	public IntVarVisitor getIntVar() {
		return new JavaIntVarVisitor();
	}

	public ArrayIntVisitor getArrayInt() {
		return new JavaArrayIntVisitor();
	}

	public WhileVisitor getWhile() {
		return new JavaWhileVisitor();
	}

	public DoTimesVisitor getDoTimes() {
		return new JavaDoTimesVisitor();
	}

	public PrintVisitor getPrint() {
		return new JavaPrintVisitor();
	}

	public AttribVisitor getAttrib() {
		return new JavaAttribVisitor();
	}
}
