package com.prudential.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

	@Value("${pathConfig}")
	private String pathConfig;

	public String getPathConfig() {
		return pathConfig;
	}

	public void setPathConfig(String pathConfig) {
		this.pathConfig = pathConfig;
	}

	
}