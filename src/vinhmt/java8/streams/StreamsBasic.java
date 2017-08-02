package vinhmt.java8.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import vinhmt.java8.Apple;
import vinhmt.java8.functional.LambdaExpressions;

public final class StreamsBasic
{

	public static void main(String[] args)
	{
		simpleStreaming();

		moreStreamOperations();
	}

	private static void simpleStreaming()
	{
		System.out.println("===== BEFORE FILTERING =====");
		List<Apple> apples = Apple.getDefaultAppleList();
		apples.stream().forEach(System.out::println);

		System.out.println("\n===== AFTER FILTERING =====");
		apples.stream()
				.filter((apple) -> apple.getWeightInGram() > 100)
				.sorted(Comparator.comparing(Apple::getWeightInGram))
				.map(Apple::getColor)
				.distinct()
				.limit(3)
				.forEach(System.out::println);

		System.out.println("\n===== A STREAM CAN BE CONSUMED ONLY ONCE =====");
		Stream<String> blah = Arrays.asList("Java 8", "Streams", "Are", "Awesome").stream();
		blah.forEach(System.out::println);
		try
		{
			blah.forEach(System.out::println);
		}
		catch (IllegalStateException ex)
		{
			System.out.println("Exception: " + ex.getMessage());
		}
	}

	private static void moreStreamOperations()
	{
		System.out.println("\n===== MORE STREAM OPERATIONS =====");

		List<Apple> apples = Apple.getDefaultAppleList();

		System.out.println("\n===== SKIPPING =====");
		apples.stream()
				.skip(2) // skip yellow and green - exception free
				.limit(3)
				.forEach(System.out::println);

		System.out.println("\n===== MULTIPLE MAPPINGS =====");
		List<String> appleColors = apples.stream()
				.map(Apple::getColor)
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		LambdaExpressions.forEach(appleColors, System.out::println);

		System.out.println("\n===== FLAT MAP =====");
		appleColors.stream()
				.map(word -> word.split("")) // create a Stream<String[]> from
												// all characters of all colors
				.flatMap(Arrays::stream) // group all streams of String[] into a
											// single stream of String
				.limit(3) // limit to the first 3 characters of red
				.forEach(System.out::println);
	}

}
