package visitor_imp.cpp_visitor;

import model.CodeDataStructureVisitorFactory;
import visitor.ArrayIntVisitor;
import visitor.AttribVisitor;
import visitor.DeclareScopeVisitor;
import visitor.DoTimesVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.DefineScopeVisitor;
import visitor.WhileVisitor;

public class CppGeneratorCodeDataStructureVisitorFactory implements
		CodeDataStructureVisitorFactory {

	public DefineScopeVisitor getDefineScope() {
		return new CppDefineScopeVisitor();
	}

	public DeclareScopeVisitor getDeclareScope() {
		return new CppDeclareScopeVisitor();
	}

	public IntVarVisitor getIntVar() {
		return new CppIntVarVisitor();
	}

	public ArrayIntVisitor getArrayInt() {
		return new CppArrayIntVisitor();
	}

	public WhileVisitor getWhile() {
		return new CppWhileVisitor();
	}

	public DoTimesVisitor getDoTimes() {
		return new CppDoTimesVisitor();
	}

	public PrintVisitor getPrint() {
		return new CppPrintVisitor();
	}

	public AttribVisitor getAttrib() {
		return new CppAttribVisitor();
	}
}
