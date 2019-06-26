package com.youngman.mopbatch.domain.clubdailystatistics.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by YoungMan on 2019-06-26.
 */

@Service
@RequiredArgsConstructor
public class ClubDailyStatisticsFindDao {

	private final ClubDailyStatisticsRepository clubDailyStatisticsRepository;


	/**
	 * 일일 조회
	 */
	public List<String> findChairEmailsByStatisticsDate() {
		return clubDailyStatisticsRepository.findChairEmailsByStatisticsDate(LocalDate.now());
	}
}
