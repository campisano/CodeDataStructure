package test;

import java_visitor.JavaGeneratorCodeDataStructureVisitorFactory;
import model.CodeDataStructureVisitorFactory;
import model.FactoryCodeDataStructure;
import cpp_visitor.CppGeneratorCodeDataStructureVisitorFactory;

public class TestDoTimes extends FactoryCodeDataStructure {
	public TestDoTimes(CodeDataStructureVisitorFactory visitor) {
		super(visitor);
		
		PROGRAM(
			DEFINITION(
				DECLARE_CONST("times", 10),
				DEFINE_SCOPE("Scope",
					ARRAY_INT("result", VALUE("times")),
					ARRAY_INT("left", VALUE("times")),
					ARRAY_INT("right", VALUE("times"))
				)
			),
			DECLARE_SCOPE("Scope", "scope"),
			DO_TIMES(	
				VALUE("scope"),
				ATTRIB( //TODO [CMP] or BLOCK(),
					ARRAY_ITEM("scope.result", "time"),
					ADD(
						ARRAY_ITEM("scope.left", "time"),
						ARRAY_ITEM("scope.right", "time"))
				),
				VALUE("times"))
		);
	}

	public static void main(String[] args) {
		System.out.println("--------------------");
		new TestDoTimes(new CppGeneratorCodeDataStructureVisitorFactory());
		System.out.println("--------------------");
		new TestDoTimes(new JavaGeneratorCodeDataStructureVisitorFactory());
		System.out.println("--------------------");
	}
}
