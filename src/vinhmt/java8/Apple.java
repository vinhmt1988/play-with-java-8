package vinhmt.java8;

public final class Apple
{
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

	public String toString()
	{
		return this.getColor()
				+ " apple: "
				+ this.getWeightInGram()
				+ "g.";
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((color == null) ? 0 : color.hashCode());
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
