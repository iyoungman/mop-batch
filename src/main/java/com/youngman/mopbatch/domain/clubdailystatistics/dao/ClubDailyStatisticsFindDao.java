package com.youngman.mopbatch.domain.clubdailystatistics.dao;

import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubSignCount;
import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubSignCountResponse;
import com.youngman.mopbatch.domain.clubdailystatistics.entity.ClubDailyStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        LocalDate startDay = LocalDate.now().minusDays(15);
        LocalDate endDay = LocalDate.now();
        List<ClubSignCount> clubSignCounts = clubDailyStatisticsRepository.findByStatisticsDate(clubId, startDay, endDay);

        return refineSignCount(startDay, clubSignCounts);
    }

    private ClubSignCountResponse refineSignCount(LocalDate startDay, List<ClubSignCount> clubSignCounts) {
        List<ClubSignCount> result = new ArrayList<>();
        int check = 0;
        for (int i = 0; i < 15; i++) {
            if (check < clubSignCounts.size() && startDay.isEqual(clubSignCounts.get(check).getStatisticsDate())) {
                ClubSignCount clubSignCount = clubSignCounts.get(check);
                clubSignCount.convertToDay();
                result.add(clubSignCount);
                check++;
            } else {
                ClubSignCount emptyClubSignCount = new ClubSignCount(startDay, 0);
                emptyClubSignCount.convertToDay();
                result.add(emptyClubSignCount);
            }
            startDay = startDay.plusDays(1);
        }

        return new ClubSignCountResponse(result);
    }
}
