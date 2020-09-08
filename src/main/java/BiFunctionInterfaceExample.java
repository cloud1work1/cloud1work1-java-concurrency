package main.java;

import java.util.function.BiFunction;

public class BiFunctionInterfaceExample {

	
	static BiFunction<String, String, Integer> bif1 = (a,b) -> {
		System.out.println(a.concat(b));
		return a.concat(b).length();
	};
	
	public static void main(String[] args) {
		System.out.println(bif1.apply("Hello", "World"));
	}
}
