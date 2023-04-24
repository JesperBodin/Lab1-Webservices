module org.example.consumer {
requires org.example.service;
uses org.example.service.CurrencyConverter;
uses org.example.service.annotation.Currency;
}
