package com.youngman.mopbatch.domain.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by YoungMan on 2019-06-24.
 */

@Embeddable
@NoArgsConstructor
public class ClubInfo {

	private Long clubId;
	private String clubName;


	@Builder
	public ClubInfo(Long clubId, String clubName) {
		this.clubId = clubId;
		this.clubName = clubName;
	}
}
