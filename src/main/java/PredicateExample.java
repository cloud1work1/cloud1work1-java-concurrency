package main.java;

import java.util.function.Predicate;

public class PredicateExample {

	
	public static void main(String[] args) {
		Predicate<Integer> prdeicate = (number) -> (number>=100);
		
		System.out.println(prdeicate.test(123));
	}
}
