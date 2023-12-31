package com.currency.service;

import com.currency.dto.Currency;
import com.currency.dto.CurrencyConversion;
import com.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        List<Currency> currencies =  currencyRepository.findAll();
        currencies.sort(Comparator.comparing(Currency::getName));
        return currencies;
    }

    public Optional<Double> convertCurrency(CurrencyConversion currencyConversion) {
        if (currencyConversion.getTo() != null && currencyConversion.getFrom() != null && currencyConversion.getValue() >= 0) {
            var currencyConvertibleValue = currencyRepository.findById(currencyConversion.getFrom().toUpperCase()).orElseThrow();
            var currencyConvertValue = currencyRepository.findById(currencyConversion.getTo().toUpperCase()).orElseThrow();
            var tempResult = currencyConvertValue.getValueInEuros() * currencyConversion.getValue() / currencyConvertibleValue.getValueInEuros();
            var bigDecimal = new BigDecimal(Double.toString(tempResult));
            bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
            return Optional.of(bigDecimal.doubleValue());
        }
        return Optional.empty();
    }
}
