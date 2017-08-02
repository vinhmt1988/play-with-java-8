package vinhmt.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import vinhmt.java8.Apple;

public final class StreamsIntermediate
{
	private static final List<Integer> NUMBERS_1 = Arrays.asList(1, 2, 3);
	private static final List<Integer> NUMBERS_2 = Arrays.asList(3, 4);

	public static void main(String[] args)
	{
		nestedMapsToHandleNumericArrays();

		matching();

		mapReduce();
	}

	private static void nestedMapsToHandleNumericArrays()
	{
		System.out.println("===== NESTED MAPS =====");

		System.out.println("\n===== SOURCES =====");
		System.out.println(NUMBERS_1);
		System.out.println(NUMBERS_2);

		System.out.println("\n===== COMBINE 2 ARRAYS =====");
		List<int[]> arrays = NUMBERS_1.stream().flatMap(i -> NUMBERS_2.stream().map(j -> new int[]
		{ i, j })).collect(Collectors.toList());
		printArrays(arrays);

		System.out.println("\n===== COMBINE 2 ARRAYS WITH CONDITION =====");
		List<int[]> filteredArrays = NUMBERS_1.stream()
				.flatMap(i -> NUMBERS_2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]
				{ i, j })).collect(Collectors.toList());
		printArrays(filteredArrays);
	}

	private static void printArrays(List<int[]> arrays)
	{
		for (int[] array : arrays)
		{
			System.out.println("{" + array[0]
					+ ", "
					+ array[1]
					+ "}");
		}
	}

	private static void matching()
	{
		System.out.println("\n===== MATCHING =====");
		System.out.println("NUMBERS_1 contains 3: " + NUMBERS_1.stream().anyMatch(n -> n == 3));

		System.out.println("\n===== FINDING ANY =====");
		Apple.getDefaultAppleList()
				.stream()
				.filter(apple -> "green".equals(apple.getColor()))
				.findAny()
				.ifPresent(System.out::println);
	}

	private static void mapReduce()
	{
		System.out.println("\n===== MAP - REDUCE =====");
		// Integer reduce = Apple.getDefaultAppleList().stream().map(apple ->
		// 1).reduce(0, (a, b) -> a + b);
		Double totalWeight = Apple.getDefaultAppleList().stream().map(apple -> apple.getWeightInGram()).reduce(0.0,
				Double::sum);
		System.out.println("\tSum: " + totalWeight);
		Optional<Double> optionalMaxWeight = Apple.getDefaultAppleList()
				.stream()
				.map(apple -> apple.getWeightInGram())
				.reduce(Double::max);
		System.out.println("\tMax weight: " + (optionalMaxWeight.isPresent() ? optionalMaxWeight.get() : 0));
	}

}
