package com.springcloud.limitservices.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("limit-service")
@Getter
@Setter
@Component
public class ConfigureProperties {

	private int min;
	private int max;

}
