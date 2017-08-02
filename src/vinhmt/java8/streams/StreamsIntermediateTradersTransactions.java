package vinhmt.java8.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class StreamsIntermediateTradersTransactions
{
	private static final Trader RAOUL = new Trader("Raoul", "Cambridge");
	private static final Trader MARIO = new Trader("Mario", "Milan");
	private static final Trader ALAN = new Trader("Alan", "Cambridge");
	private static final Trader BRIAN = new Trader("Brian", "Cambridge");
	private static final List<Trader> TRADERS = Arrays.asList(RAOUL, MARIO, ALAN, BRIAN);

	private static final List<Transaction> TRANSACTIONS = Arrays.asList(new Transaction(BRIAN, 2011, 300),
			new Transaction(RAOUL, 2012, 1000),
			new Transaction(RAOUL, 2011, 400),
			new Transaction(MARIO, 2012, 710),
			new Transaction(MARIO, 2012, 700),
			new Transaction(ALAN, 2012, 950));

	public static void main(String[] args)
	{
		performTraderOperations();
		performTransactionOperations();
	}

	private static void performTraderOperations()
	{
		System.out.println("===== TRADER OPERATIONS =====");

		System.out.println("\n===== Unique cities =====");
		TRADERS.stream().map(Trader::getCity).distinct().forEach(System.out::println);

		System.out.println("\n===== Traders from Cambridge =====");
		TRADERS.stream()
				.filter(trader -> "Cambridge".equals(trader.getCity()))
				.sorted(Comparator.comparing(Trader::getName))
				.forEach(System.out::println);

		System.out.println("\n===== Any traders based in Milan? =====");
		System.out.println(TRADERS.stream().anyMatch(trader -> "Milan".equals(trader.getCity())));

		System.out.println("\n===== Sorted traders' names =====");
		String tradersNames = TRADERS.stream()
				.map(Trader::getName)
				.sorted(String::compareTo)
				// reduce is not efficient here
				// .reduce("", (name1, name2) -> name1 + ", " + name2)
				.collect(Collectors.joining(", "));
		// remove the first ", "
		System.out.println(tradersNames);
	}

	private static void performTransactionOperations()
	{
		System.out.println("\n===== TRANSACTION OPERATIONS =====");

		System.out.println("\n===== All transactions in 2011 sorted by value =====");
		TRANSACTIONS.stream()
				.filter(transaction -> 2011 == transaction.getYear())
				.sorted(Comparator.comparing(Transaction::getValue))
				.forEach(System.out::println);

		System.out.println("\n===== All transactions of the traders from Cambridge =====");
		TRANSACTIONS.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity())).forEach(
				System.out::println);

		System.out.println("\n===== The highest value of all the transactions =====");
		System.out.println(TRANSACTIONS.stream().map(Transaction::getValue).reduce(Integer::max).get());

		System.out.println("\n===== The transaction with the smallest value =====");
		// ===== first way: sort and find first =====
		// Transaction transactionWithSmallestValue = TRANSACTIONS.stream()
		// .sorted(Comparator.comparing(Transaction::getValue))
		// .findFirst()
		// .get();
		// ===== second way: repeatedly compare the values with the reduce
		// method =====
		// Transaction transactionWithSmallestValue = TRANSACTIONS.stream()
		// .reduce((transaction1, transaction2) -> transaction1.getValue() <
		// transaction2.getValue() ? transaction1
		// : transaction2)
		// .get();
		// ===== third way: use the min method =====
		Transaction transactionWithSmallestValue = TRANSACTIONS.stream()
				.min(Comparator.comparing(Transaction::getValue))
				.get();
		System.out.println(transactionWithSmallestValue);
	}

	private static class Trader
	{
		private final String name;
		private final String city;

		public Trader(String name, String city)
		{
			this.name = name;
			this.city = city;
		}

		public String getName()
		{
			return this.name;
		}

		public String getCity()
		{
			return this.city;
		}

		public String toString()
		{
			return "Trader: " + this.name
					+ " in "
					+ this.city;
		}
	}

	private static class Transaction
	{
		private final Trader trader;
		private final int year;
		private final int value;

		public Transaction(Trader trader, int year, int value)
		{
			this.trader = trader;
			this.year = year;
			this.value = value;
		}

		public Trader getTrader()
		{
			return this.trader;
		}

		public int getYear()
		{
			return this.year;
		}

		public int getValue()
		{
			return this.value;
		}

		public String toString()
		{
			return "{ " + this.trader
					+ ", "
					+ "year: "
					+ this.year
					+ ", "
					+ "value: "
					+ this.value
					+ " }";
		}
	}
}
