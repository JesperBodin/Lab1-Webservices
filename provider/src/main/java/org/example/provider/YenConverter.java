package org.example.provider;

import org.example.service.CurrencyConverter;

public class YenConverter implements CurrencyConverter {


	@Override
	public double getCurrencyConverter() {
		return 0.076;
	}
}
