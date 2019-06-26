package com.youngman.mopbatch.domain.member.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-26.
 */

@Service
@RequiredArgsConstructor
public class MemberFindDao {

	private final MemberRepository memberRepository;


	public List<String> findFcmTokensByChairEmails(List<String> chairEmails) {
		return memberRepository.find
	}
}
