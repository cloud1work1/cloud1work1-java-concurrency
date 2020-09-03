package main.java;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

	public static void main(String[] args) {
		String sentence = "Hello, how are you ? Iam fine thank you . Hope all is well";
		Map<String, Long> valueMap = Stream.of(sentence.split(" ")).collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum));
		System.out.println(valueMap);
		List<String> valueList = Stream.of(sentence.split(" ")).distinct().collect(Collectors.toList());
		System.out.println(valueList);
		Set<String> valueSet = Stream.of(sentence.split(" ")).collect(Collectors.toSet());
		System.out.println(valueSet);
	}
}
