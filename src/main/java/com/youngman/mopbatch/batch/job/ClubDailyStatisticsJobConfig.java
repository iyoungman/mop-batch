package com.youngman.mopbatch.batch.job;

import com.youngman.mopbatch.repository.MyClubRepository;
import com.youngman.mopbatch.domain.entity.MyClub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
	private final MyClubRepository myClubRepository;

	private static final int chunkSize = 10;


	@Bean
	public Job clubDailyStatisticsJob() {
		return jobBuilderFactory.get("clubDailyStatisticsJob")
				.preventRestart()
				.start(clubDailyStatisticsStep())
				.build();
	}

	@Bean
	@JobScope
	public Step clubDailyStatisticsStep() {
		return stepBuilderFactory.get("clubDailyStatisticsStep")
				.<MyClub, MyClub>chunk(chunkSize)
				.reader(clubDailyStatisticsReader())
				.writer(clubDailyStatisticsWriter())
				.build();
	}

	@Bean
	public JpaPagingItemReader<MyClub> clubDailyStatisticsReader() {
		JpaPagingItemReader<MyClub> jpaPagingItemReader = new JpaPagingItemReader<>();
		jpaPagingItemReader.setQueryString("select mc from MyClub as mc where mc.createdDate = :createdDate");

		Map<String, Object> map = new HashMap<>();
		map.put("createdDate", LocalDate.now());

		jpaPagingItemReader.setParameterValues(map);
		jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory); //(4)
		jpaPagingItemReader.setPageSize(chunkSize);
		return jpaPagingItemReader;
	}

	private ItemWriter<MyClub> clubDailyStatisticsWriter() {
		return list -> {
			for (MyClub myClub: list) {
				log.info("Current myClub => {}", myClub);
			}
		};
	}

}
