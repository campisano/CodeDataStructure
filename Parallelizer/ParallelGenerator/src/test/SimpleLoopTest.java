package test;

import visitor_imp.cpp_visitor.CppGeneratorCodeDataStructureVisitorFactory;
import visitor_imp.java_visitor.JavaGeneratorCodeDataStructureVisitorFactory;
import model.CodeDataStructureVisitorFactory;
import model.FactoryCodeDataStructure;

public class SimpleLoopTest extends FactoryCodeDataStructure {

	public SimpleLoopTest(CodeDataStructureVisitorFactory visitor) {
		super(visitor);
		
		PROGRAM(
				VAR_INT("I"),
				VAR_INT("J"),
				WHILE(
						LT(
								VALUE("I"),
								CONST(10)
						),
						ATTRIB(
								VALUE("J"),
								ADD(
										VALUE("J"),
										CONST(10)
								)
						),
						ATTRIB(
								VALUE("I"),
								ADD(
										VALUE("I"),
										CONST(1)
								)
						)
				),
				PRINT(
						VALUE("I")
				),
				PRINT(
						VALUE("J")
				)
		);
	}

	public static void main(String[] args) {
		System.out.println("--------------------");
		new SimpleLoopTest(new CppGeneratorCodeDataStructureVisitorFactory());
		System.out.println("--------------------");
		new SimpleLoopTest(new JavaGeneratorCodeDataStructureVisitorFactory());
		System.out.println("--------------------");
		// ouptut :
		/*
		--------------------
		int I;
		int J;
		while (I<10)
		{
		    J = J+10;
		    I = I+1;
		}
		std::cout << I;
		std::cout << J;
		--------------------
		int I;
		int J;
		while (I<10) {
		    J = J+10;
		    I = I+1;
		}
		System.out.print(I);
		System.out.print(J);
		--------------------
		 */
	}
}