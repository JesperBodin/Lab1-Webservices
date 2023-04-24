package org.example.provider;

import org.example.service.CurrencyConverter;

public class EurConverter implements CurrencyConverter {
	@Override
	public double getCurrencyConverter() {
		return 10.55;
	}
}
