/**
 * 
 */
package com.m7md.couponSystemSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author scary
 *
 */
@Configuration
public class CorsConfiguration {

	@Bean
	public WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurer() {
			public void addMapping(CorsRegistry registery) {
				registery.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

}
