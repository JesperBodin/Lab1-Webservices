package org.example.provider;

import org.example.service.CurrencyConverter;
import org.example.service.annotation.Currency;

@Currency("YEN")
public class Yen implements CurrencyConverter {

	@Override
	public double getCurrency(double amount) {
		return amount * 13.09;
	}
}
