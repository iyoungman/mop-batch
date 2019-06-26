package com.youngman.mopbatch.infra.fcm.service;

import com.youngman.mopbatch.global.interceptor.HeaderRequestInterceptor;
import com.youngman.mopbatch.infra.fcm.constant.FcmConstant;
import com.youngman.mopbatch.infra.fcm.dto.FirebaseResponse;
import com.youngman.mopbatch.infra.fcm.dto.NotificationResponse;
import com.youngman.mopbatch.infra.fcm.dto.PushResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by YoungMan on 2019-06-25.
 */

@Slf4j
@Service
public class PushNotificationService {

	public FirebaseResponse sendNotification(List<String> fcmTokens) {

		PushResponse pushResponse = PushResponse.builder()
				.notificationResponse(NotificationResponse.of())
				.registrationIds(fcmTokens)
				.build();

		HttpEntity<PushResponse> request = new HttpEntity<>(pushResponse);

		CompletableFuture<FirebaseResponse> pushNotification = this.send(request);
		CompletableFuture.allOf(pushNotification).join();

		FirebaseResponse firebaseResponse = null;

		try {
			firebaseResponse = pushNotification.get();
		} catch (InterruptedException | ExecutionException e) {
			log.info(e.getMessage());
		}
		return firebaseResponse;
	}

	@Async
	public CompletableFuture<FirebaseResponse> send(HttpEntity<PushResponse> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FcmConstant.KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		FirebaseResponse firebaseResponse = restTemplate.postForObject(FcmConstant.URL, entity, FirebaseResponse.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}
}
