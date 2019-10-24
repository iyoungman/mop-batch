package com.youngman.mopbatch.batch;

import com.youngman.mopbatch.global.batch.job.ClubDailyStatisticsJobConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by YoungMan on 2019-07-04.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ClubDailyStatisticsJobConfig.class, BatchTestConfig.class})
public class ClubDailyStatisticsJobTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	@Qualifier(value = "clubDailyStatisticsJob")
	private Job clubDailyStatisticsJob;


	@Before
	public void setUp() {
		jobLauncherTestUtils.setJobLauncher(jobLauncher);
		jobLauncherTestUtils.setJobRepository(jobRepository);
		jobLauncherTestUtils.setJob(clubDailyStatisticsJob);
	}

	@Test
	public void 동호회_일일통계_Job_테스트() throws Exception {

		//given
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("jobName", "clubDailyStatisticsJob")
				.addString("statisticsDate", LocalDate.now().toString())
				.toJobParameters();

		//when
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

		//then
		assertThat(BatchStatus.COMPLETED, is(jobExecution.getStatus()));
	}

}
