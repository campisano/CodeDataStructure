package test;

import model.CodeDataStructureVisitorFactory;
import model.FactoryCodeDataStructure;
import java_visitor.JavaGeneratorCodeDataStructureVisitorFactory;
import cpp_visitor.CppGeneratorCodeDataStructureVisitorFactory;

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
				),
				ARRAYINT("result", 10),
				ARRAYINT("left", 10),
				ARRAYINT("right", 10),
				DOTIMES(
						SCOPE(
							VALUE("result"),	//TODO [CMP] ou ARRAYINT("result", 10), aqui?
							VALUE("left"),		//TODO [CMP] ou ARRAYINT("left", 10), aqui?
							VALUE("right")		//TODO [CMP] ou ARRAYINT("right", 10) aqui?
						),
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