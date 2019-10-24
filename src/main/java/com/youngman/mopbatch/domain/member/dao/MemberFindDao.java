package com.youngman.mopbatch.domain.member.dao;

import com.youngman.mopbatch.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YoungMan on 2019-06-26.
 */

@Service
@RequiredArgsConstructor
public class MemberFindDao {

    private final MemberRepository memberRepository;

    public List<String> findFcmTokensByEmails(List<String> emails) {
        List<Member> members = memberRepository.findByEmailIn(emails);
        return fetchFcmTokens(members);
    }

    private List<String> fetchFcmTokens(List<Member> members) {
        return members.stream()
                .map(Member::getFcmToken)
                .distinct()
                .collect(Collectors.toList());
    }
}
