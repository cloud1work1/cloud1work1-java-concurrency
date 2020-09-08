package main.java;

import java.util.function.UnaryOperator;

public class UnaryOperatorExample {

	
	public static void main(String[] args) {
		UnaryOperator<String> o1 = param -> param.toUpperCase();
		UnaryOperator<Integer> o2 = param -> param * 10;
		System.out.println(o1.apply("hello, how are you? unary operator"));
		System.out.println(o2.apply(100));
	}
}
