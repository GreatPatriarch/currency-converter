package com.currency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyConverterControllerWeb {

    @GetMapping("/")
    public String convert() {
        return "conversion";
    }
}
