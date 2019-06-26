package com.youngman.mopbatch.domain.clubdailystatistics.entity;

import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubDailyStatisticsDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by YoungMan on 2019-06-22.
 */

@Entity
@Table(name = "club_daily_statistics_tbl")
@Getter
@NoArgsConstructor
public class ClubDailyStatistics {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statistics_id")
	private Long statisticsId;

	private LocalDate statisticsDate;

	private Long todaySignCount;

	private Long myClubId;

	@Embedded
	private ClubInfo clubInfo;

	@Embedded
	private MemberInfo memberInfo;


	@Builder
	public ClubDailyStatistics(LocalDate statisticsDate, Long todaySignCount, Long myClubId,
							   ClubInfo clubInfo, MemberInfo memberInfo) {

		this.statisticsDate = statisticsDate;
		this.todaySignCount = todaySignCount;
		this.myClubId = myClubId;
		this.clubInfo = clubInfo;
		this.memberInfo = memberInfo;
	}

	public static ClubDailyStatistics of(ClubDailyStatisticsDto clubDailyStatisticsDto) {

		ClubInfo clubInfo = ClubInfo.builder()
				.clubId(clubDailyStatisticsDto.getClubId())
				.clubName(clubDailyStatisticsDto.getClubName())
				.chairEmail(clubDailyStatisticsDto.getChairEmail())
				.build();

		MemberInfo memberInfo = MemberInfo.builder()
				.memberEmail(clubDailyStatisticsDto.getMemberEmail())
				.memberName(clubDailyStatisticsDto.getMemberName())
				.build();

		return ClubDailyStatistics.builder()
				.statisticsDate(clubDailyStatisticsDto.getStatisticsDate())
				.todaySignCount(clubDailyStatisticsDto.getCount())
				.myClubId(clubDailyStatisticsDto.getMyClubId())
				.clubInfo(clubInfo)
				.memberInfo(memberInfo)
				.build();
	}
}
