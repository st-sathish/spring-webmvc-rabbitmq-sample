package com.sathish.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * To resolve @Value(${}) annotation placeholder in the application manner
 * @author Sathish Thangathurai
 *
 */
@Configuration
public class ApplicationConfiguration {
	
    /**
	 * Property placeholder configurer needed to process @Value annotations
	*/
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
