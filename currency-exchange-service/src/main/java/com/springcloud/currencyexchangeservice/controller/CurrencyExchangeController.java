package com.springcloud.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.currencyexchangeservice.model.CurrencyExchangeModel;
import com.springcloud.currencyexchangeservice.repo.CurrencyRepository;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	@Autowired
	Environment environment;

	@Autowired
	CurrencyRepository currencyRepository;

	@GetMapping("/from/{from}/to/{to}")
	public CurrencyExchangeModel getCurrencyExchangeModel(@PathVariable String from, @PathVariable String to) {

		CurrencyExchangeModel currencyExchangeModel = new CurrencyExchangeModel();
		try {
//		currencyExchangeModel.setConversionMultiple(BigDecimal.valueOf(50));
//		currencyExchangeModel.setFrom(from);
//		currencyExchangeModel.setTo(to);
//		currencyExchangeModel.setId(12345L);

			System.out.println("from : " + from);
			System.out.println("to : " + to);

			String port = environment.getProperty("local.server.port");

			currencyExchangeModel = currencyRepository.findByFromAndTo(from, to).get(0);

			currencyExchangeModel.setEnviroment(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currencyExchangeModel;
	}

}
