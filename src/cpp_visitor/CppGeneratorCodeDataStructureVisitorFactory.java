package cpp_visitor;

import simple_model.CodeDataStructureVisitorFactory;
import visitor.AttribVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.WhileVisitor;

public class CppGeneratorCodeDataStructureVisitorFactory implements
		CodeDataStructureVisitorFactory {

	public IntVarVisitor getIntVar() {
		return new CppIntVarVisitor();
	}

	public WhileVisitor getWhile() {
		return new CppWhileVisitor();
	}

	public PrintVisitor getPrint() {
		return new CppPrintVisitor();
	}

	public AttribVisitor getAttrib() {
		return new CppAttribVisitor();
	}
}
