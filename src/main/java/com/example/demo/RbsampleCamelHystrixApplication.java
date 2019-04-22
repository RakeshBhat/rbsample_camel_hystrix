package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RbsampleCamelHystrixApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(RbsampleCamelHystrixApplication.class, args);
		/*ApplicationContext ac = new SpringApplication(RbsampleCamelHystrixApplication.class).run(args);
		CamelSpringBootApplicationController cac = ac.getBean(CamelSpringBootApplicationController.class);
		cac.run();*/
	}

}
