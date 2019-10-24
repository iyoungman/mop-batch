//package com.youngman.mopbatch.domain.clubdailystatistics.dao;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNull.notNullValue;
//import static org.junit.Assert.assertThat;
//
///**
// * Created by YoungMan on 2019-06-27.
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ClubDailyStatisticsFindDaoTest {
//
//	@Autowired
//	private ClubDailyStatisticsFindDao clubDailyStatisticsFindDao;
//
//
//	@Test
//	public void findChairEmailsByStatisticsDate() {
//
//		//when
//		List<String> chairEmails = clubDailyStatisticsFindDao.findChairEmailsByStatisticsDate();
//
//		//then
//		assertThat(chairEmails, is(notNullValue()));
//	}
//}
