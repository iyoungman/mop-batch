package com.youngman.mopbatch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by YoungMan on 2019-06-22.
 */

@Configuration
@EnableBatchProcessing
@EnableJpaAuditing
public class AppConfig {
}
