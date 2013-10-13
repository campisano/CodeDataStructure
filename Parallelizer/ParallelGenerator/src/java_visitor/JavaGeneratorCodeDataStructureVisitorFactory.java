package java_visitor;

import model.CodeDataStructureVisitorFactory;
import visitor.AttribVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.WhileVisitor;

public class JavaGeneratorCodeDataStructureVisitorFactory implements
		CodeDataStructureVisitorFactory {

	public IntVarVisitor getIntVar() {
		return new JavaIntVarVisitor();
	}

	public WhileVisitor getWhile() {
		return new JavaWhileVisitor();
	}

	public PrintVisitor getPrint() {
		return new JavaPrintVisitor();
	}

	public AttribVisitor getAttrib() {
		return new JavaAttribVisitor();
	}
}
