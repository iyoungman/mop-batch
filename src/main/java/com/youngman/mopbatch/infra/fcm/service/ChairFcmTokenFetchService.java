package com.youngman.mopbatch.infra.fcm.service;

import com.google.api.gax.rpc.InvalidArgumentException;
import com.youngman.mopbatch.domain.clubdailystatistics.dao.ClubDailyStatisticsFindDao;
import com.youngman.mopbatch.domain.member.dao.MemberFindDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-26.
 */

@Service
@RequiredArgsConstructor
public class ChairFcmTokenFetchService {

    private final ClubDailyStatisticsFindDao clubDailyStatisticsFindDao;
    private final MemberFindDao memberFindDao;

    public List<String> fetchChairFcmTokenByStatisticsDate() {
        List<String> chairEmails = clubDailyStatisticsFindDao.findChairEmailsByStatisticsDate();
        if (chairEmails.size() == 0) {
            throw new RuntimeException("새로운 인원이 가입한 동호회가 없습니다.");
        }
        return memberFindDao.findFcmTokensByEmails(chairEmails);
    }
}
