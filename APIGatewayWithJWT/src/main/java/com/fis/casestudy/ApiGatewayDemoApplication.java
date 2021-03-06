package com.fis.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ApiGatewayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayDemoApplication.class, args);
	}

}


/*
 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
 * password = "password"; String encodedPassword =
 * passwordEncoder.encode(password);
 * 
 * System.out.println(); System.out.println("Password is         : " +
 * password); System.out.println("Encoded Password is : " + encodedPassword);
 * System.out.println("Invalid Password is : " + encodedPassword + "junk");
 * System.out.println();
 */