package com.youngman.mopbatch.domain.clubdailystatistics.dao;

import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubSignCount;
import com.youngman.mopbatch.domain.clubdailystatistics.entity.ClubDailyStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by YoungMan on 2019-06-22.
 */

public interface ClubDailyStatisticsRepository extends JpaRepository<ClubDailyStatistics, Long> {

	List<ClubDailyStatistics> findByStatisticsDate(LocalDate statisticsDate);


	@Query(value =
			"SELECT new com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubSignCount(c.statisticsDate, c.todaySignCount) " +
			"FROM ClubDailyStatistics c " +
			"WHERE c.clubInfo.clubId = :clubId AND c.statisticsDate > :startDate AND c.statisticsDate < :endDate " +
			"ORDER BY c.statisticsDate ASC")
	List<ClubSignCount> findByStatisticsDate(@Param("clubId") Long clubId,
											 @Param("startDate") LocalDate startDate,
											 @Param("endDate") LocalDate endDate);
}
