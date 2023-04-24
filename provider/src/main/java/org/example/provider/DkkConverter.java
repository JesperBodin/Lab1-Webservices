package org.example.provider;

import org.example.service.CurrencyConverter;

public class DkkConverter implements CurrencyConverter {
	@Override
	public double getCurrencyConverter() {
		return 1.52;
	}
}
