//package com.youngman.mopbatch.batch;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.test.JobLauncherTestUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by YoungMan on 2019-07-04.
// */
//
//@Configuration
//public class BatchTestConfig {
//
//	@Autowired
//	@Qualifier(value = "clubDailyStatisticsJob")
//	private Job clubDailyStatisticsJob;
//
//	@Bean
//	public JobLauncherTestUtils jobLauncherTestUtils() {
//		JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
////		jobLauncherTestUtils.setJob(clubDailyStatisticsJob);
//		return jobLauncherTestUtils;
//	}
//}
