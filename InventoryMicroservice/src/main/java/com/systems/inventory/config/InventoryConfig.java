package com.systems.inventory.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
