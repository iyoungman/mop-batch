package com.youngman.mopbatch.domain.member.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by YoungMan on 2019-06-27.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberFindDaoTest {

	@Autowired
	private MemberFindDao memberFindDao;


	@Test
	public void findFcmTokensByChairEmails() {

		//given
		List<String> emails = Arrays.asList("test", "test2");

		//when
		List<String> fcmTokens = memberFindDao.findFcmTokensByChairEmails(emails);

		//then
		assertThat(fcmTokens, is(notNullValue()));
	}
}
