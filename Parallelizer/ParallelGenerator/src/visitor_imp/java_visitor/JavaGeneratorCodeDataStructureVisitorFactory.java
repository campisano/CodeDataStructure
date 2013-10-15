package visitor_imp.java_visitor;

import model.CodeDataStructureVisitorFactory;
import visitor.ArrayIntVisitor;
import visitor.AttribVisitor;
import visitor.DeclareScopeVisitor;
import visitor.DoTimesVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.DefineScopeVisitor;
import visitor.WhileVisitor;

public class JavaGeneratorCodeDataStructureVisitorFactory implements
		CodeDataStructureVisitorFactory {

	public DefineScopeVisitor getDefineScope() {
		return new JavaDefineScopeVisitor();
	}

	public DeclareScopeVisitor getDeclareScope() {
		return new JavaDeclareScopeVisitor();
	}

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
