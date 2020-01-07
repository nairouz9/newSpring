package com.m7md.couponSystemSpring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.m7md.couponSystemSpring.login.Session;

@SpringBootApplication
@ComponentScan({ "com.m7md.couponSystemSpring" })
public class CouponSystemSpringApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(CouponSystemSpringApplication.class, args);
		System.out.println("run Application");
	}

	@Bean
	public Map<String, Session> tokens() {
		return new HashMap<String, Session>();
	}
}
