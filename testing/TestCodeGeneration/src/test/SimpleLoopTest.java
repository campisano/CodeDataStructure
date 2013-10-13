package test;

import java_visitor.JavaGeneratorCodeDataStructureVisitorFactory;
import cpp_visitor.CppGeneratorCodeDataStructureVisitorFactory;
import simple_model.CodeDataStructureVisitorFactory;
import simple_model.FactoryCodeDataStructure;

public class SimpleLoopTest extends FactoryCodeDataStructure {

	public SimpleLoopTest(CodeDataStructureVisitorFactory visitor) {
		super(visitor);
		
		PROGRAM(
				INTVAR("I"),
				INTVAR("J"),
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
		// --------------------
		// int I;
		// int J;
		// while (I<10) {
		//     J = J+10;
		//     I = I+1;
		// }
		// std::cout << I;
		// std::cout << J;
		// --------------------
		// int I;
		// int J;
		// while (I<10) {
		//     J = J+10;
		//     I = I+1;
		// }
		// System.out.print(I);
		// System.out.print(J);
		// --------------------
	}
}