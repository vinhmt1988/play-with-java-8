package vinhmt.java8.functional;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import vinhmt.java8.Apple;

public final class MethodReferences
{

	public static void main(String[] args)
	{
		testBiFunction();

		testTriFunction();

		testSorting();

		handleEmail();
	}

	private static void testBiFunction()
	{
		BiFunction<String, Double, Apple> appleFunction = Apple::new;
		Apple newApple = appleFunction.apply("new", Double.valueOf(1000));
		System.out.println(newApple.toString());
	}

	private static void testTriFunction()
	{
		TriFunction<String, String, String, Trinity> triFunction = Trinity::new;
		Trinity jLTrinity = triFunction.apply("Batman", "Superman", "Wonder woman");
		System.out.println("\nJustice League Trinity:\n" + jLTrinity.getFirst()
				+ "\n"
				+ jLTrinity.getSecond()
				+ "\n"
				+ jLTrinity.getThird());
	}

	private static void testSorting()
	{
		List<Apple> apples = Apple.getDefaultAppleList();

		System.out.println("\n===== SORTED APPLES BY WEIGHT =====");
		apples.sort(Comparator.comparing(Apple::getWeightInGram));
		LambdaExpressions.forEach(apples, System.out::println);

		System.out.println("\n===== REVERSEDLY SORTED APPLES BY WEIGHT =====");
		apples.sort(Comparator.comparing(Apple::getWeightInGram).reversed());
		LambdaExpressions.forEach(apples, System.out::println);

		System.out.println("\n===== SORTED APPLES BY WEIGHT THAN BY COLOR =====");
		apples.sort(Comparator.comparing(Apple::getWeightInGram).thenComparing(Apple::getColor));
		LambdaExpressions.forEach(apples, System.out::println);
	}

	private static void handleEmail()
	{
		System.out.println("\n===== EMAIL =====");
		Function<String, String> headerHandler = Mail::addHeader;
		Function<String, String> mailHandler = headerHandler.andThen(Mail::checkSpelling).andThen(Mail::addFooter);
		String mail = mailHandler.apply("You have successfully unsubscribed from our services.@#)^");
		System.out.println(mail);
	}

	private static interface TriFunction<T, U, V, R>
	{
		R apply(T t, U u, V v);
	}

	private static class Trinity
	{
		private final String first;
		private final String second;
		private final String third;

		public Trinity(String first, String second, String third)
		{
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public String getFirst()
		{
			return first;
		}

		public String getSecond()
		{
			return second;
		}

		public String getThird()
		{
			return third;
		}

	}

	private static class Mail
	{
		public static String addHeader(String text)
		{
			return "Dear customer,\n" + text;
		}

		public static String checkSpelling(String text)
		{
			return text.replaceAll("[^a-zA-Z0-9 .,!?\n]", "");
		}

		public static String addFooter(String text)
		{
			return text + "\nBest regards.";
		}
	}
}
