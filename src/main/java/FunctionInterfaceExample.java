package main.java;

import java.util.function.Function;

public class FunctionInterfaceExample {

	public static void main(String[] args) {
		Function<String,String> f1 = param -> param.toUpperCase();
		System.out.println(f1.apply("simple function interface"));
		
		Function<String,String> f2 = param -> param.concat(", applying andthen method");
		System.out.println(f1.andThen(f2).apply("simple function interface"));
		
		System.out.println(f1.compose(f2).apply("simple function interface, along with compose method"));
	}
}
