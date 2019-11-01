package com.youngman.mopbatch.domain.clubdailystatistics.aplication;

import com.youngman.mopbatch.domain.clubdailystatistics.dao.ClubDailyStatisticsFindDao;
import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubSignCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by YoungMan on 2019-11-02.
 */

@Service
@RequiredArgsConstructor
public class ClubStatisticsService {

    private final ClubDailyStatisticsFindDao clubDailyStatisticsFindDao;

    public ClubSignCountResponse findSignCount(Long clubId) {
        return clubDailyStatisticsFindDao.findSignCount(clubId);
    }

}
