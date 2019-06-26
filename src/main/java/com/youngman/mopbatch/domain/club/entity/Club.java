package com.youngman.mopbatch.domain.club.entity;

import com.youngman.mopbatch.domain.model.BaseDate;
import com.youngman.mopbatch.domain.myclub.entity.MyClub;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-06-22.
 */

@Entity
@Table(name = "club_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends BaseDate implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "club_id")
	private Long id;

	private String name;

	private String introduce;

	private String region;

	private String hobby;

	private String chairEmail;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
	private List<MyClub> myClubs = new ArrayList<>();

	@Builder
	public Club(String name, String introduce, String region,
				String hobby,String chairEmail, List<MyClub> myClubs) {

		this.name = name;
		this.introduce = introduce;
		this.region = region;
		this.hobby = hobby;
		this.chairEmail = chairEmail;
		this.myClubs = myClubs;
	}

}
