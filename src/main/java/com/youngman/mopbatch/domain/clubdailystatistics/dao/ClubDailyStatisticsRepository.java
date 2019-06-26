package com.youngman.mopbatch.domain.clubdailystatistics.dao;

import com.youngman.mopbatch.domain.clubdailystatistics.entity.ClubDailyStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by YoungMan on 2019-06-22.
 */

public interface ClubDailyStatisticsRepository extends JpaRepository<ClubDailyStatistics, Long> {

	List<String> findChairEmailsByStatisticsDate(LocalDate statisticsDate);
}
