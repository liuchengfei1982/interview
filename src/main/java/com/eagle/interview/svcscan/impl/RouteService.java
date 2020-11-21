package com.eagle.interview.svcscan.impl;

import com.eagle.interview.svcscan.RouteRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

	@Autowired
	List<RouteRule> listRule;

	public void printRoute(){
		for (RouteRule routeRule : listRule) {
			System.out.println("the type is>"+routeRule.type());
		}
	}
}
