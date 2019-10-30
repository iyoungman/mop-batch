package com.youngman.mopbatch.global.batch.job;

import com.youngman.mopbatch.domain.clubdailystatistics.entity.ClubDailyStatistics;
import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubDailyStatisticsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YoungMan on 2019-06-22.
 */

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ClubDailyStatisticsJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final EntityManagerFactory entityManagerFactory;
	private final JobCompletionListener jobCompletionListener;

	private static final int CHUNK_SIZE = 10;

	@Bean
	public Job clubDailyStatisticsJob() {
		return jobBuilderFactory.get("clubDailyStatisticsJob")
				.start(clubDailyStatisticsStep(null))
                .incrementer(new RunIdIncrementer())
//                .preventRestart()// 항상 처음부터 재시작
				.listener(jobCompletionListener)
				.build();
	}

	@Bean
	@JobScope
	public Step clubDailyStatisticsStep(@Value("#{jobParameters[jobName]}") String jobName) {
		return stepBuilderFactory.get("clubDailyStatisticsStep")
				.<ClubDailyStatisticsDto, ClubDailyStatistics>chunk(CHUNK_SIZE)// Reader 에서 반환할 타입, Writer 에 파라미터로 넘어올 타입
				.reader(clubDailyStatisticsReader(null))
				.processor(clubDailyStatisticsProcessor())
				.writer(clubDailyStatisticsWriter())
				.build();
	}

	@Bean
	@StepScope
	public JpaPagingItemReader<ClubDailyStatisticsDto> clubDailyStatisticsReader(@Value("#{jobParameters[statisticsDate]}") String statisticsDate) {
		JpaPagingItemReader<ClubDailyStatisticsDto> jpaPagingItemReader = new JpaPagingItemReader<>();
		jpaPagingItemReader.setQueryString(
				"select new com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubDailyStatisticsDto" +
						"(mc.createdDate, (SELECT count(*) FROM MyClub mc2 WHERE mc.club = mc2.club), mc.id, c.id, c.name, c.chairEmail, m.email, m.name) " +
						"from MyClub mc " +
						"join mc.club c " +
						"join mc.member m " +
						"where mc.createdDate = :createdDate " +
						"group by c.id, m.id " +
						"order by c.id asc"
		);

		Map<String, Object> map = new HashMap<>();
		map.put("createdDate", LocalDate.parse(statisticsDate).minusDays(1));

		jpaPagingItemReader.setParameterValues(map);
		jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
		jpaPagingItemReader.setPageSize(CHUNK_SIZE);
		return jpaPagingItemReader;
	}

	@Bean
	public ItemProcessor<ClubDailyStatisticsDto, ClubDailyStatistics> clubDailyStatisticsProcessor() {
		return ClubDailyStatistics::of;
	}

	@Bean
	public ItemWriter<ClubDailyStatistics> clubDailyStatisticsWriter() {
		JpaItemWriter<ClubDailyStatistics> jpaItemWriter = new JpaItemWriter<>();
		jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
		return jpaItemWriter;
	}

}
