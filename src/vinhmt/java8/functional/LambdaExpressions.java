package vinhmt.java8.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import vinhmt.java8.Apple;

public final class LambdaExpressions
{

	public static void main(String[] args)
	{
		// supplier
		System.out.println("===== APPLY SUPPLIER =====");
		List<Apple> allApples = Arrays.asList(create(() -> new Apple("red", 150)),
				create(() -> new Apple("yellow", 120)),
				create(() -> new Apple("green", 200)),
				create(() -> new Apple("black", 110)),
				create(() -> new Apple("white", 40.4)));

		// consumer
		System.out.println("\n===== APPLY CONSUMER =====");
		// method reference: conveniently print Apple elements in allApples
		forEach(allApples, System.out::println);

		// predicate
		System.out.println("\n===== APPLY PREDICATE =====");
		List<Apple> filteredApples = filter(allApples, (Apple apple) -> apple.getWeightInGram() >= 120);
		forEach(filteredApples, apple -> System.out.println(apple.toString()));

		// function
		System.out.println("\n===== APPLY FUNCTION =====");
		List<String> appleColors = map(allApples, (Apple apple) -> apple.getColor());
		forEach(appleColors, color -> System.out.println(color));

		// bi-predicate
		System.out.println("\n===== APPLY BI-PREDICATE =====");
		System.out.println("Apple colors contain 'black': " + test(appleColors, "black", List::contains));
	}

	public static <T> T create(Supplier<T> supplier)
	{
		return supplier.get();
	}

	public static <T> void forEach(List<T> source, Consumer<T> consumer)
	{
		for (T ele : source)
		{
			consumer.accept(ele);
		}
	}

	public static <T> List<T> filter(List<T> source, Predicate<T> predicate)
	{
		List<T> filtered = new ArrayList<>();
		for (T ele : source)
		{
			if (predicate.test(ele))
			{
				filtered.add(ele);
			}
		}
		return filtered;
	}

	public static <T, R> List<R> map(List<T> source, Function<T, R> function)
	{
		List<R> results = new ArrayList<>();
		for (T ele : source)
		{
			results.add(function.apply(ele));
		}
		return results;
	}

	public static <T, U> boolean test(T list, U ele, BiPredicate<T, U> biPredicate)
	{
		return biPredicate.test(list, ele);
	}

}
