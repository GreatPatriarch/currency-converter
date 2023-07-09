package com.currency.controller;

import com.currency.dto.Currency;
import com.currency.dto.CurrencyConversion;
import com.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyConverterController {

    private final CurrencyService currencyService;
    @GetMapping("/all")
    public ResponseEntity<List<Currency>> AllCurrency() {
        return new ResponseEntity<>(currencyService.getAllCurrencies(), HttpStatus.OK);
    }

    @PostMapping("/convert")
    public ResponseEntity<Optional<Double>> convertCurrency(@RequestBody CurrencyConversion currencyConversion) {
        var result = currencyService.convertCurrency(currencyConversion);
        if (result.isPresent()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
