package vinhmt.java8.functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import vinhmt.java8.Apple;

public final class BehaviorParameterization
{

	public static void main(String[] args)
	{
		List<Apple> allApples = Apple.getDefaultAppleList();

		// filter for apples which weigh more than 100g and have their color
		// neither is black nor white
		Predicate<Apple> weightPredicate = (Apple apple) -> apple.getWeightInGram() > 100;
		Predicate<Apple> colorPredicate = (Apple apple) -> "black".equals(apple.getColor())
				|| "white".equals(apple.getColor());
		filterApples(allApples, weightPredicate.and(colorPredicate.negate()));

		// sort apples by their weight
		Comparator<Apple> byWeight = (Apple apple1, Apple apple2) ->
		{
			return apple1.getWeightInGram() > apple2.getWeightInGram() ? 1 : -1;
		};
		Collections.sort(allApples, byWeight);
		System.out.println("\n===== SORTED APPLES =====");
		for (Apple apple : allApples)
		{
			System.out.println(apple.toString());
		}
	}

	private static List<Apple> filterApples(List<Apple> allApples, Predicate<Apple> applePredicate)
	{
		List<Apple> filteredApples = new ArrayList<>();
		for (Apple apple : allApples)
		{
			if (applePredicate.test(apple))
			{
				filteredApples.add(apple);
				System.out.println("Added " + apple.toString());
			}
		}
		return filteredApples;
	}

}
