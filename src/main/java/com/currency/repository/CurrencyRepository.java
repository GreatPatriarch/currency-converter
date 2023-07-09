package com.currency.repository;

import com.currency.dto.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CurrencyRepository extends CrudRepository<Currency, String> {
    @Override
    List<Currency> findAll();
}
