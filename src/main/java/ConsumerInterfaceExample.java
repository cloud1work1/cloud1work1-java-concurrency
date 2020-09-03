package main.java;

import java.util.function.Consumer;

public class ConsumerInterfaceExample {

	public static void main(String[] args) {
		Consumer<String> consumer1 = (s) -> { System.out.println("From consumer 1 " + s.toUpperCase());};
		consumer1.accept("Hello how are you? Consumer Interface");
		Consumer consumer2 = (s) -> System.out.println("From consumer 2 "+ s);
		consumer2.accept("Hope you are doing fine, Consumer Interface");
		Consumer consumer3 = (s) -> System.out.println("joining using andThen");
		consumer3.andThen(consumer2).andThen(consumer1).accept("S");
	}
}
