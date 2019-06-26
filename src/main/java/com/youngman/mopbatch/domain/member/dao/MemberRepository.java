package com.youngman.mopbatch.domain.member.dao;

import com.youngman.mopbatch.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-22.
 */

public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("select m from Member m where m.email in :emails")
	List<Member> findByEmailIn(List<String> emails);
}
