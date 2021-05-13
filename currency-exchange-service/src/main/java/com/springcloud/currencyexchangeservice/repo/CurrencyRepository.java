package com.springcloud.currencyexchangeservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springcloud.currencyexchangeservice.model.CurrencyExchangeModel;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyExchangeModel, Long> {

	List<CurrencyExchangeModel> findByFromAndTo(String from, String to);

}
