package test;

import model.CodeDataStructureVisitorFactory;
import model.FactoryCodeDataStructure;
import java_visitor.JavaGeneratorCodeDataStructureVisitorFactory;
import cpp_visitor.CppGeneratorCodeDataStructureVisitorFactory;

public class SimpleLoopTest extends FactoryCodeDataStructure {

	public SimpleLoopTest(CodeDataStructureVisitorFactory visitor) {
		super(visitor);
		
		PROGRAM(
				DEFINITION(
					DEFINESCOPE("Scope",
							ARRAYINT("result", 10),
							ARRAYINT("left", 10),
							ARRAYINT("right", 10)
						),

						INTVAR("times")
				),
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
				),
				DECLARESCOPE("Scope", "scope"),
				DOTIMES(
						VALUE("scope"),
//						BLOCK(),
						ATTRIB(
							ARRAYITEM("scope.result", "time"),
							ADD(
								ARRAYITEM("scope.left", "time"),
								ARRAYITEM("scope.right", "time"))
						),
						CONST(10))
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