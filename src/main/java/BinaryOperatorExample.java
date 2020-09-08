package main.java;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorExample {

	public static void main(String[] args) {
		BinaryOperator<Integer> bo1 = BinaryOperator.maxBy((a,b) -> a>b?1:a==b?0:-1);
		
		System.out.println("BinaryOperator1 example with use of inline comparator and maxby results: "+ bo1.apply(100, 50));
		
		Comparator<String> strcom = (a,b) -> a.compareTo(b);
		BinaryOperator<String> bo2 = BinaryOperator.maxBy(strcom);
		
		System.out.println("BinaryOperator2 example with standard comparator and minby results: "+ bo2.apply("HELLO", "hello"));
		
	}

}
