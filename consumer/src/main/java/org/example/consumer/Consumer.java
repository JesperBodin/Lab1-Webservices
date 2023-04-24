package org.example.consumer;

import org.example.service.CurrencyConverter;
import org.example.service.annotation.Currency;
import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;


public class Consumer {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		boolean flag = true;
		while(flag){
			printMenu();

			switch (sc.nextLine()) {
				case "1" -> {
					System.out.println("You choose Danish Crowns (DKK)");
					System.out.println("Enter amount of SEK you want to convert: ");
					try {
						convertToDkk(Double.parseDouble(sc.nextLine()));
						flag = askToMakeAnotherConversion();
					} catch (Exception e){
						System.out.println("Invalid Input - Try again");
					}
				}
				case "2" -> {
					System.out.println("You choose Euros (EUR)");
					System.out.println("Enter amount of SEK you want to convert:");
					try {
						convertToEur(Double.parseDouble(sc.nextLine()));
						flag = askToMakeAnotherConversion();
					} catch (Exception e) {
						System.out.println("Invalid Input - Try again");
					}
				}
				case "3" -> {
					System.out.println("You choose Japanese Yen (Yen)");
					System.out.println("Enter amount of SEK you want to convert:");
					try {
						convertToYen(Double.parseDouble(sc.nextLine()));
						flag = askToMakeAnotherConversion();
					} catch (Exception e) {
						System.out.println("Invalid Input - Try again");
					}
				}
				case "0" -> {
					System.out.println("Closing...");
					System.exit(0);
				}
				default -> System.out.println("Bad input - Try again");
			}
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
    Please choose a Currency to convert from SEK
    1.Danish Crown (DKK)
    2.Euros (EUR)
    3.Japanese Yen (YEN)
    0.EXIT PROGRAM
    				""");
	}
}
