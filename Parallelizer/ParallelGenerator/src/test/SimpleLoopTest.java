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
						)
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
				DOTIMES(
						VALUE("result"),	//TODO [CMP] ou SCOPE("scope") aqui?
//						BLOCK(),
						ATTRIB(
							ARRAYITEM("result", "time"),
							ADD(
								ARRAYITEM("left", "time"),
								ARRAYITEM("right", "time"))
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