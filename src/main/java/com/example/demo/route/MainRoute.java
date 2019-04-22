package com.example.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MainRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("direct:mainRoute")
		.setHeader("UserType", simple("userA"))
		.to("direct:UserRouteWithHystrix");
		

		from("direct:routeWithOutHystrix")
		.setHeader("UserType", simple("userA"))
		.to("direct:UserRouteWithOutHystrixRoute");

	}

}
