package vinhmt.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Apple
{
	private static final List<Apple> DEFAULT_APPLE_LIST = Arrays.asList(new Apple("red", 150),
			new Apple("yellow", 120),
			new Apple("green", 200),
			new Apple("blue", 110),
			new Apple("black", 110),
			new Apple("white", 40.4),
			new Apple("colorless", 40));

	private final String color;
	private final double weightInGram;

	public Apple(String color, double weightInGram)
	{
		this.color = color;
		this.weightInGram = weightInGram;
	}

	public String getColor()
	{
		return color;
	}

	public double getWeightInGram()
	{
		return weightInGram;
	}

	public static List<Apple> getDefaultAppleList()
	{
		return new ArrayList<>(DEFAULT_APPLE_LIST);
	}

	public String toString()
	{
		return this.getColor() + " apple: "
				+ this.getWeightInGram()
				+ "g.";
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apple other = (Apple) obj;
		if (color == null)
		{
			if (other.color != null)
				return false;
		}
		else if (!color.equals(other.color))
			return false;
		return true;
	}

}
