package com.youngman.mopbatch.domain.member.entity;

import com.youngman.mopbatch.domain.model.BaseDate;
import com.youngman.mopbatch.domain.myclub.entity.MyClub;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-06-22.
 */

@Entity
@Table(name = "member_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseDate implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(unique = true)
	private String email;

	private String pw;

	private String name;

	private String mobile;

	private String address;

	private String introduce;

	private String fcmToken;

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<MyClub> myClubs = new ArrayList<>();

	@Builder
	public Member(String email, String pw, String name, String mobile,
				  String address, String introduce, String fcmToken) {

		this.email = email;
		this.pw = pw;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.introduce = introduce;
		this.fcmToken = fcmToken;
	}
}

