package test;

import java.math.BigInteger;
import java.util.Random;

import function.EuclideanAlgorithmFunction;

public class TestEuclideanAlgorithm{

	public TestEuclideanAlgorithm() {
		Random ran = new Random();

		int a = Math.abs(ran.nextInt()) % 100 + 1;
		int b = Math.abs(ran.nextInt()) % 1000 + 1;
		int result;

		try {
			result = new EuclideanAlgorithmFunction(a, b).evaluate();
			
			int gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
			if (result != gcd) {
				throw new Exception("TestEuclideanAlgorithm() failed: " + result + " != " + gcd);
			}
		
			System.out.print("TestEuclideanAlgorithm passed.");
		} catch (Exception _exc) {
			_exc.printStackTrace();
			System.out.print("TestEuclideanAlgorithm failed!");
		}
	}
	
	public static void main(String[] args) {
		new TestEuclideanAlgorithm();
	}
}
