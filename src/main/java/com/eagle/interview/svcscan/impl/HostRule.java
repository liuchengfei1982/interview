package com.eagle.interview.svcscan.impl;

import com.eagle.interview.svcscan.RouteRule;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(1)
public class HostRule implements RouteRule {
	@Override
	public String type() {
		return "HostRule";
	}
}
