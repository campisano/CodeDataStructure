package visitor_imp.opencl_visitor;

import model.CodeDataStructureVisitorFactory;
import visitor.ArrayIntVisitor;
import visitor.AttribVisitor;
import visitor.DeclareScopeVisitor;
import visitor.DoTimesVisitor;
import visitor.IntVarVisitor;
import visitor.PrintVisitor;
import visitor.DefineScopeVisitor;
import visitor.WhileVisitor;

public class OpenCLGeneratorCodeDataStructureVisitorFactory implements
		CodeDataStructureVisitorFactory {

	public DefineScopeVisitor getDefineScope() {
		return new OpenCLDefineScopeVisitor();
	}

	public DeclareScopeVisitor getDeclareScope() {
		return new OpenCLDeclareScopeVisitor();
	}

	public IntVarVisitor getIntVar() {
		return new OpenCLIntVarVisitor();
	}

	public ArrayIntVisitor getArrayInt() {
		return new OpenCLArrayIntVisitor();
	}

	public WhileVisitor getWhile() {
		return new OpenCLWhileVisitor();
	}

	public DoTimesVisitor getDoTimes() {
		return new OpenCLDoTimesVisitor();
	}

	public PrintVisitor getPrint() {
		return new OpenCLPrintVisitor();
	}

	public AttribVisitor getAttrib() {
		return new OpenCLAttribVisitor();
	}
}
