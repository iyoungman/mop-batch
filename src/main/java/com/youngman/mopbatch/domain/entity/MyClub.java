package com.youngman.mopbatch.domain.entity;

import com.youngman.mopbatch.domain.BaseDate;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "club_id")
	private Club club;

	@Builder
	public MyClub(Member member, Club club) {
		this.member = member;
		this.club = club;
	}

	public static MyClub of(Member member, Club club) {
		return MyClub.builder()
				.member(member)
				.club(club)
				.build();
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		super.setModifiedDate(modifiedDate);
	}
}
