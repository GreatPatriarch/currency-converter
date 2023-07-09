package com.currency.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CurrencyDTO {
    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;

}
