package com.youngman.mopbatch.infra.fcm.service;

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
		return memberFindDao.findFcmTokensByChairEmails(chairEmails);
	}
}
