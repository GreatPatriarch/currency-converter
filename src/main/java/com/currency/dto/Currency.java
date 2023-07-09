package com.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Currency")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    private String name;
    private Double valueInEuros;
}
