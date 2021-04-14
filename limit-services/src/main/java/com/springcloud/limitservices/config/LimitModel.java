package com.springcloud.limitservices.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LimitModel {

	private int minimum;
	private int maximum;

}
