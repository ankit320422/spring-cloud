package com.springcloud.limitservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.limitservices.config.ConfigureProperties;
import com.springcloud.limitservices.config.LimitModel;

@RestController
@RequestMapping("/limit")
public class LimitController {

	@Autowired
	ConfigureProperties configureProperties;

	@GetMapping("/get")
	public LimitModel get() {

		return new LimitModel(configureProperties.getMin(), configureProperties.getMax());
	}

}
