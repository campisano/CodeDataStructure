package test;

import java.math.BigInteger;
import java.util.Random;

import model.EuclideanAlgorithmFunction;

public class TestEuclideanAlgorithm {

	public static void main(String[] args) {
		Random ran = new Random();

		int a = Math.abs(ran.nextInt()) % 100 + 1;
		int b = Math.abs(ran.nextInt()) % 1000 + 1;
		int c = -1;
		int d = -1;

		try {
			c = new EuclideanAlgorithmFunction(a, b).evaluate();
			d = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
			if (c != d) {
				throw new Exception("TestEuclideanAlgorithm() failed!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());

			return;
		} finally {
			System.out.println("a: " + a);
			System.out.println("b: " + b);
			System.out.println("EuclideanAlgorithmFunction.evaluate(): " + c);
			System.out.println("BigInteger a.gcd(b): " + d);
		}

		System.out.println("TestEuclideanAlgorithm() success!");
	}
}
