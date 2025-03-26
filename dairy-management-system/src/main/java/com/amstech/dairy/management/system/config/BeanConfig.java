package com.amstech.dairy.management.system.config;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.core.util.ObjectMapperFactory;

public class BeanConfig {

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
