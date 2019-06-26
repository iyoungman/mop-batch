package com.youngman.mopbatch.domain.member.dao;

import com.youngman.mopbatch.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YoungMan on 2019-06-22.
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
}
