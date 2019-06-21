package com.youngman.mopbatch.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * Created by YoungMan on 2019-06-22.
 */

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDate {

	@CreatedDate
	private LocalDate createdDate;

	@LastModifiedDate
	private LocalDate modifiedDate;
}
