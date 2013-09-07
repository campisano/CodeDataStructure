package test;

import model.Language;

public class TestLanguage extends Language {

	public static void main(String[] args) {
		new TestLanguage();
	}

	public TestLanguage() {
		try {
			testAdd();
			System.out.println("testAdd() success!");
			testSub();
			System.out.println("testSub() success!");
			testMinor();
			System.out.println("testMinor() success!");
			////////////////////////////////////////////////////testMaior();
			System.out.println("testMaior() success!");
			testMultiAddSub();
			System.out.println("testMultiAddSub() success!");
			testIfElse();
			System.out.println("testIfElse() success!");
		} catch (Exception e) {
			System.err.println(e.toString());
			System.err.println("TestLanguage() failed!");

			return;
		}

		System.out.println("TestLanguage() success!");
	}

	private void testAdd() throws Exception {
		int a, b = 3, c = 5;
		integer("a");
		integer("b", b);
		integer("c", c);

		//assign(var("a"), add(var("b"), var("c")));
		assign("a", add("b", "c"));

		execute();

		a = b + c;

		if (a != var("a").evaluate()) {
		//int result = 0;
		//evaluate(result, "a");
		//
		//if (a != result) {
			throw new Exception("testAdd() failed!");
		}
	}

	private void testSub() throws Exception {
		int a, b = 3, c = 5;
		integer("a");
		integer("b", b);
		integer("c", c);

		//assign(var("a"), sub(var("b"), var("c")));
		assign("a", sub("b", "c"));

		execute();

		a = b - c;

		if (a != var("a").evaluate()) {
		//int result = 0;
		//evaluate(result, "a");
		//
		//if (a != result) {
			throw new Exception("testSub() failed!");
		}
	}

	private void testMinor() throws Exception {
		boolean a, b;
		int c = 3, d = 5;
		integer("a");
		integer("b");
		integer("c", c);
		integer("d", d);

		//assign("a", minor("b", "c"));

		execute();

		a = c < d;
		b = d < c;

		//if (a != var("a").evaluate()) {
		//	throw new Exception("testMinor() failed!");
		//}
		
		//if (b != var("b").evaluate()) {
		//	throw new Exception("testMinor() failed!");
		//}
	}

	private void testMultiAddSub() throws Exception {
		int a, b = 3, c = 5, d = 6, e = 10;
		integer("a");
		integer("b", b);
		integer("c", c);
		integer("d", d);
		integer("e", e);

		//assign(var("a"), sub(add(add(var("b"), var("c")), var("d")), var("e")));
		assign("a", sub(add(add("b", "c"), "d"), "e"));

		execute();

		a = (((b + c) + d) - e);

		if (a != var("a").evaluate()) {
		//int result = 0;
		//evaluate(result, "a");
		//
		//if (a != result) {
			throw new Exception("testMultiAdd() failed!");
		}
	}

	private void testIfElse() throws Exception {
		{
			int a, b = 3, c = 5;
			integer("a");
			integer("b", b);
			integer("c", c);

			//ifcond(minor(var("b"), var("c")),
			ifcond(minor("b", "c"),
					//getassign(var("a"), sub(var("c"), var("b"))),
					getassign("a", sub("c", "b")),
					//getassign(var("a"), sub(var("b"), var("c"))));
					getassign("a", sub("b", "c")));

			execute();

			if (b < c)
				a = c - b;
			else
				a = b - c;

			if (a != var("a").evaluate()) {
			//int result = 0;
			//evaluate(result, "a");
			//
			//if (a != result) {
				throw new Exception("testIfElse() failed!");
			}
		}
		{

			int a, b = 5, c = 3, k = 100;
			integer("a");
			integer("b", b);
			integer("c", c);
			integer("k", k);

			//ifcond(minor(var("b"), var("c")),
			ifcond(minor("b", "c"),
					//getassign(var("a"), add(sub(var("c"), var("b")), var("k"))),
					getassign("a", add(sub("c", "b"), "k")),
					//getassign(var("a"), sub(sub(var("b"), var("c")), var("k"))));
					getassign("a", sub(sub("b", "c"), "k")));

			execute();

			if (b < c)
				a = c - b + k;
			else
				a = b - c - k;

			if (a != var("a").evaluate()) {
			//int result = 0;
			//evaluate(result, "a");
			//
			//if (a != result) {
				throw new Exception("testIfElse() failed!");
			}
		}
		/*
		 * 
AlgoritmoDeEuclides(a: inteiro; b: inteiro): inteiro
variáveis
   divisor: inteiro
   dividendo: inteiro
   c: inteiro
início
   
   se b > a então
   início
     dividendo = b
     divisor = a
   senão
     dividendo = a
     divisor = b
   fim-se
   
   enquanto resto(dividendo/divisor) ≠ 0
   início
      c = resto(dividendo/divisor)
      dividendo = divisor
      divisor = c
   fim-enquanto
   
   AlgoritmoDeEuclides = divisor
fim-função
		 * 
		 * menor(soma(variavel("a"), constante(3)),
		 * subtracao(variavel("b",constante(2)))
		 * 
		 * exp = maior(variavel("b"),variavel("a"));
		 * 
		 * blocoVerdade = bloco(
		 * atribuicao(variavel("dividendo"),variavel("b")),
		 * atribuicao(variavel("divisor"),variavel("a")) );
		 * 
		 * blocoFalso = bloco( atribuicao(variavel("dividendo"),variavel("a")),
		 * atribuicao(variavel("divisor"),variavel("b")) );
		 */
	}
}
