package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;

@RunWith(CamelSpringBootRunner.class)
@ActiveProfiles("utest")
@UseAdviceWith
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@BootstrapWith(SpringBootTestContextBootstrapper.class)
public class UserRouteWithOutHystrixTest {

	private static Logger logger = LoggerFactory.getLogger(UserRouteWithOutHystrixTest.class);

	@Autowired
	public CamelContext camelContext;

	@EndpointInject(uri = "direct:routeWithOutHystrix")
	public ProducerTemplate directUser;

	@Value("${user.endpoint}")
	public String userHost;

	private boolean isCamelContextInitialized = false;

	@Before
	public void initializeCamelContext() throws Exception {
		if (!isCamelContextInitialized) {
			logger.info("Waiting for Camel Context to become initialized.");
			Thread.sleep(5000L);
		}
	}

	@Before
	public void setUp() throws Exception {

		camelContext.getRouteDefinitions().stream().forEach(r -> {
	   		logger.info("getRouteDefinitions().getId {}", r.getId());
		});

		camelContext.getRouteDefinition("userHostWithOutHystrixId").autoStartup(true).adviceWith(camelContext,
				new AdviceWithRouteBuilder() {

					@Override
					public void configure() throws Exception {

						this.interceptSendToEndpoint(userHost).skipSendToOriginalEndpoint().process(e -> {
							String body = "cachedUser";
							e.getIn().setBody(body);
						});
					}
				});

		camelContext.start();
	}

	@Test
	public void userData_WithOutHystrix_successfully() throws Exception {

		Map<String, Object> headers = new HashMap<>();
		headers.put("UserType", "userS");
		Exchange in = new DefaultExchange(camelContext);
		Exchange out = null;
		in.getIn().setHeaders(headers);

		this.directUser.start();
		out = directUser.send(in);
		String op = out.getIn().getBody(String.class);
		this.directUser.stop();

		assertNotNull(op);
		assertTrue(op.contains("cachedUser"));
	}

}
