package com.example.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserRouteWithHystrix extends RouteBuilder {
	
	@Value("${user.endpoint}")
	public String userHost;

	@Override
	public void configure() throws Exception {

		from("direct:UserRouteWithHystrix")
		.routeId("fromHystrixId")
		.hystrix()
		.to(userHost)
		.routeId("withHystrixId")
			.convertBodyTo(String.class)
			.bean(UserService.class, "validateResponse")
			//.routeId("validateResponseId")
			.bean(UserService.class, "cacheData")
			//.routeId("cacheDataId")
			.endHystrix()
		.onFallback()
		//.routeId("UserRouteWithHystrixFallBackId")
			.bean(UserService.class, "getCachedData")
			.routeId("getCachedDataId")
		.end();
		

	}

}
