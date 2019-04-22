package com.example.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserRouteWithOutHystrixRoute extends RouteBuilder{

	@Value("${user.endpoint}")
	public String userHost;

	@Override
	public void configure() throws Exception {
		from("direct:UserRouteWithOutHystrixRoute")
		.routeId("withOutHystrixId") //1
		.to(userHost)
		.routeId("userHostWithOutHystrixId") //2
		.convertBodyTo(String.class)
		.bean(UserService.class, "validateResponse")
		//.routeId("validateResponseId") //<!--- during test camel context finds only this route id not 1 and 2, why?  -->
		.end();		
	}

}
