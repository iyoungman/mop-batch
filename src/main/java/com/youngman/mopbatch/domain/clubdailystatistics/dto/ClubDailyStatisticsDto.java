package com.youngman.mopbatch.domain.clubdailystatistics.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * Created by YoungMan on 2019-06-24.
 */

@Getter
@NoArgsConstructor
public class ClubDailyStatisticsDto {

	private LocalDate statisticsDate;
	private Long count;
	private Long myClubId;
	private Long clubId;
	private String clubName;
	private String chairEmail;
	private String memberEmail;
	private String memberName;


	@Builder
	public ClubDailyStatisticsDto(LocalDate statisticsDate, Long count, Long myClubId, Long clubId,
								  String clubName, String chairEmail, String memberEmail, String memberName) {

		this.statisticsDate = statisticsDate;
		this.count = count;
		this.myClubId = myClubId;
		this.clubId = clubId;
		this.clubName = clubName;
		this.chairEmail = chairEmail;
		this.memberEmail = memberEmail;
		this.memberName = memberName;
	}
}