package com.youngman.mopbatch.global.batch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by YoungMan on 2019-06-25.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final JobLocator jobLocator;

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }

    /**
     * 초 분 시 일 월 요일 (연도)
     */
    @Scheduled(cron = "0 10 0 * * *")
    public void schedule() {
        try {
            jobLauncher.run(getJobByName("clubDailyStatisticsJob"), new JobParametersBuilder()
                    .addString("jobName", "clubDailyStatisticsJob")
                    .addString("statisticsDate", LocalDate.now().toString())
                    .addLong("currentTime", System.currentTimeMillis())
                    .toJobParameters()
            );
        } catch (Exception e) {
            log.info("ERROR => {}", e.getMessage());
        }
    }

    private Job getJobByName(String name) throws Exception {
        return jobLocator.getJob(name);
    }
}
