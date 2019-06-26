package com.youngman.mopbatch.domain.myclub.entity;

import com.youngman.mopbatch.domain.club.entity.Club;
import com.youngman.mopbatch.domain.member.entity.Member;
import com.youngman.mopbatch.domain.model.BaseDate;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by YoungMan on 2019-06-22.
 */

@Entity
@Table(name = "my_club_tbl")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyClub extends BaseDate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "my_club_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id")
	private Club club;

	@Builder
	public MyClub(Member member, Club club) {
		this.member = member;
		this.club = club;
	}
}
