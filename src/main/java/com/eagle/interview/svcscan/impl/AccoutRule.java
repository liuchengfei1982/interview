package com.eagle.interview.svcscan.impl;

import com.eagle.interview.svcscan.RouteRule;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AccoutRule implements RouteRule {
	@Override
	public String type() {
		return "AccoutRule";
	}
}
