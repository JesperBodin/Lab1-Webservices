package org.example.consumer;

import org.example.service.CurrencyConverter;
import org.example.service.annotation.Currency;
import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;


public class Consumer {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		String choice;

		do {
			printMenu();

			choice = sc.nextLine();

			switch (choice) {
				case "1" -> caseDKK();
				case "2" -> caseEUR();
				case "3" -> caseYEN();
				case "0" -> quit();
				default -> System.out.println("Bad input - Try again");
			}

			if (!askToMakeAnotherConversion())
				choice = String.valueOf(0);

		} while (!choice.equals("0"));
	}

	private static void quit() {
		System.out.println("Closing...");
		System.exit(0);
	}

	private static void caseYEN() {
		System.out.println("You chose Japanese Yen (Yen)");
		System.out.println("Enter amount of SEK you want to convert:");
		try {
			convertToYen(Double.parseDouble(sc.nextLine()));
		} catch (Exception e) {
			System.out.println("Invalid Input - Try again");
		}
	}

	private static void caseEUR() {
		System.out.println("You chose Euros (EUR)");
		System.out.println("Enter amount of SEK you want to convert:");
		try {
			convertToEur(Double.parseDouble(sc.nextLine()));
		} catch (Exception e) {
			System.out.println("Invalid Input - Try again");
		}
	}

	private static void caseDKK() {
		System.out.println("You chose Danish Crowns (DKK)");
		System.out.println("Enter amount of SEK you want to convert: ");
		try {
			convertToDkk(Double.parseDouble(sc.nextLine()));
		} catch (Exception e) {
			System.out.println("Invalid Input - Try again");

		}
	}



	private static List<CurrencyConverter> getCurrencyConverter(String currency) {

		ServiceLoader<CurrencyConverter> currencyConverters = ServiceLoader.load(CurrencyConverter.class);

		return currencyConverters.stream()
				.filter(c -> c.type().isAnnotationPresent(Currency.class) &&
						c.type().getAnnotation(Currency.class).value().equals(currency))
				.map(ServiceLoader.Provider::get)
				.toList();


	}
	private static void convertToDkk(double amount) {
		for (var converters : getCurrencyConverter("DKK")) {
			System.out.println( amount + " SEK is Equal to: " + converters.getCurrency(amount) + " DKK");
		}
	}
	private static void convertToEur(double amount){
		for (var converters : getCurrencyConverter("EUR")){
			System.out.println( amount + " SEK is Equal to: " + converters.getCurrency(amount) + " EUR");
		}
	}
	private static void convertToYen(double amount) {
		for (var converters : getCurrencyConverter("YEN")) {
			System.out.println( amount + " SEK is Equal to: " + converters.getCurrency(amount) + " YEN");
		}
	}

	private static boolean askToMakeAnotherConversion(){
		System.out.println("Do you want to make another conversion?(Y/N)");
		if(!sc.nextLine().equalsIgnoreCase("Y"))
			return false;
		return true;

	}
	private static void printMenu(){
		System.out.println("""
    Currency Converter
    ==================
    Please choose a Currency to convert from SEK
    1.Danish Crown (DKK)
    2.Euros (EUR)
    3.Japanese Yen (YEN)
    0.EXIT PROGRAM
    				""");
	}
}
