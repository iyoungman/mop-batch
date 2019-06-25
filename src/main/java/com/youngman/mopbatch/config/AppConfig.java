package com.youngman.mopbatch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * Created by YoungMan on 2019-06-22.
 */

@Configuration
@EnableBatchProcessing
@EnableJpaAuditing
@EnableScheduling
public class AppConfig {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
