package com.youngman.mopbatch.infra.fcm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youngman.mopbatch.infra.fcm.dto.NotificationResponse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-06-26.
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PushResponse {

	@JsonProperty(value = "notification")
	private NotificationResponse notificationResponse;

	@JsonProperty(value = "registration_ids")
	private List<String> registrationIds = new ArrayList<>();


	@Builder
	public PushResponse(NotificationResponse notificationResponse, List<String> registrationIds) {
		this.notificationResponse = notificationResponse;
		this.registrationIds = registrationIds;
	}
}
