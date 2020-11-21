package com.eagle.interview.svcscan.impl;

import com.eagle.interview.svcscan.RouteRule;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(3)
public class AreaRule implements RouteRule {
	@Override
	public String type() {
		return "AreaRule";
	}
}