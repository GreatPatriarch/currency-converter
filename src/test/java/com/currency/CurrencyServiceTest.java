package com.currency;

import com.currency.dto.Currency;
import com.currency.dto.CurrencyConversion;
import com.currency.repository.CurrencyRepository;
import com.currency.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        currencyService = new CurrencyService(currencyRepository);
    }

    @Test
    public void getAllCurrencies_ListNotEmpty() {
        when(currencyRepository.findAll()).thenReturn(Arrays.asList(new Currency("USD", 1.0),
                                                                    new Currency("EUR", 1.09)));
        List<Currency> currencies = currencyService.getAllCurrencies();
        assertFalse(currencies.isEmpty());
    }
    @Test
    void convertCurrency_withNegativeValue_shouldReturnEmptyOptional() {
        CurrencyConversion currencyConversion = new CurrencyConversion("EUR", "USD", -100.0);
        Optional<Double> result = currencyService.convertCurrency(currencyConversion);
        assertFalse(result.isPresent());
    }
    @Test
    public void testConvertCurrency() {
        CurrencyConversion currencyConversion = new CurrencyConversion("EUR", "USD", 100.0);
        when(currencyRepository.findById("EUR")).thenReturn(Optional.of(new Currency("EUR", 1.0)));
        when(currencyRepository.findById("USD")).thenReturn(Optional.of(new Currency("USD", 1.09)));

        Optional<Double> result = currencyService.convertCurrency(currencyConversion);

        assertTrue(result.isPresent());
        var roundedResult = Math.round(result.get() * 100.0) / 100.0;
        assertEquals(109.00, roundedResult);
    }
}
