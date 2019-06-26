package com.youngman.mopbatch.infra.fcm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-06-26.
 */

@Getter
@Setter
public class FirebaseResponse implements Serializable {

	@JsonProperty("multicast_id")
	private Object multicastId;

	@JsonProperty("success")
	private Integer success;

	@JsonProperty("failure")
	private Integer failure;

	@JsonProperty("canonical_ids")
	private Integer canonicalIds;

	@JsonProperty("results")
	private List<Object> results = new ArrayList<>();
}
