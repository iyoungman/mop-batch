package com.youngman.mopbatch.global.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by YoungMan on 2019-06-25.
 */

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TestJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;


	@Bean
	public Job testJob() {
		return jobBuilderFactory.get("testJob")
				.start(testStepOne(null))
				.build();
	}

	@Bean
	@JobScope
	public Step testStepOne(@Value("#{jobParameters[requestDate]}") String requestData) {
		return stepBuilderFactory.get("testStepOne")
				.tasklet((contribution, chunkContext) -> {//스텝안에 수행될 기능
					log.info(">>>>> This is Step1");
					log.info(">>>>> requestData = {}", requestData);
					return RepeatStatus.FINISHED;
				})
				.build();
	}

}
