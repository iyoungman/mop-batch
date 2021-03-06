package com.youngman.mopbatch.domain.clubdailystatistics.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by YoungMan on 2019-06-24.
 */

@Embeddable
@Getter
@NoArgsConstructor
public class MemberInfo {

	private String memberEmail;
	private String memberName;


	@Builder
	public MemberInfo(String memberEmail, String memberName) {
		this.memberEmail = memberEmail;
		this.memberName = memberName;
	}
}
