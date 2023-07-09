package com.currency.task;

import com.currency.dto.Currency;
import com.currency.dto.CurrencyDTO;
import com.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CurrencyTask {
    private final CurrencyRepository currencyRepository;
    @Value("${fixer-io-api-key}")
    private String fixerApiKey;
    @SneakyThrows
    @Scheduled(fixedRate = 2 * 1000 * 60 * 60)
    public void getRatesTask() {
        var restTemplate = new RestTemplate();
        var forObject = restTemplate.getForObject(fixerApiKey, CurrencyDTO.class);
        forObject.getRates().forEach((key, value) -> {
            var currency = new Currency(key, value);
            this.currencyRepository.save(currency);
        });
    }
}