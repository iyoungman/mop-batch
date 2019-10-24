package com.youngman.mopbatch.infra.fcm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by YoungMan on 2019-06-26.
 */

@Getter
@Setter
public class NotificationResponse {

	private String sound = "";
	private final String title = "MoP";
	private String body;

	@Builder
	public NotificationResponse(String body) {
		this.body = body;
	}

}
