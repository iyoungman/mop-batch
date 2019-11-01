package com.youngman.mopbatch.domain.clubdailystatistics.dao;

import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubSignCount;
import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubSignCountResponse;
import com.youngman.mopbatch.domain.clubdailystatistics.entity.ClubDailyStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YoungMan on 2019-06-26.
 */

@Service
@RequiredArgsConstructor
public class ClubDailyStatisticsFindDao {

    private final ClubDailyStatisticsRepository clubDailyStatisticsRepository;

    //일일 조회
    public List<String> findChairEmailsByStatisticsDate() {
        List<ClubDailyStatistics> clubDailyStatistics = clubDailyStatisticsRepository.findByStatisticsDate(LocalDate.now().minusDays(1));
        return fetchChairEmails(clubDailyStatistics);
    }

    private List<String> fetchChairEmails(List<ClubDailyStatistics> clubDailyStatistics) {
        return clubDailyStatistics.stream()
                .map(c -> c.getClubInfo().getChairEmail())
                .distinct()
                .collect(Collectors.toList());
    }

    public ClubSignCountResponse findSignCount(Long clubId) {
        List<ClubSignCount> clubSignCounts = clubDailyStatisticsRepository.findByStatisticsDate(clubId, LocalDate.now().minusDays(15), LocalDate.now());
        for (ClubSignCount clubSignCount : clubSignCounts) {
            clubSignCount.convertToDay();
        }
        return new ClubSignCountResponse(clubSignCounts);
    }
}
