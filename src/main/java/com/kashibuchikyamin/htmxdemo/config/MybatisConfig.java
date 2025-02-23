package com.kashibuchikyamin.htmxdemo.config;

import java.util.Properties;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
	@Bean
	VendorDatabaseIdProvider vendorDatabaseIdProvider() {
	  VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
	  Properties vendorProperties = new Properties();
	  vendorProperties.put("H2", "h2");
	  vendorProperties.put("MySQL", "mysql");
	  databaseIdProvider.setProperties(vendorProperties);
	  return databaseIdProvider;
	}
}
