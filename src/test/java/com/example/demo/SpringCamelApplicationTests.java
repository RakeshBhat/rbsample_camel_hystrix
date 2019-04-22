package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class SpringCamelApplicationTests {

    @EndpointInject(uri = MOCK_RESULT)
    private MockEndpoint resultEndpoint;

	@Autowired
    private CamelContext camelContext;

    @EndpointInject(uri = MOCK_TIMER)
    private ProducerTemplate producer;

    private static final String MOCK_RESULT = "mock:result";
    private static final String MOCK_TIMER = "direct:UserRouteWithOutHystrixRoute";

    @Before
	public void setup() throws Exception {

        camelContext.getRouteDefinition("userHostWithOutHystrixId")
                .autoStartup(true)
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        interceptSendToEndpoint("http4*")
                                .skipSendToOriginalEndpoint()
                                .process( e ->
            					{
            						String body = "cachedUser";
            						e.getIn().setBody(body);
            				});
                    }
                });
    }

	@Test
	public void contextLoads() throws Exception {

        resultEndpoint.expectedMessageCount(1);
        producer.sendBody("A message");
        resultEndpoint.assertIsSatisfied();
    }
}