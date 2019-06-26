package com.youngman.mopbatch.infra.fcm.api;

import com.youngman.mopbatch.infra.fcm.dto.FirebaseResponse;
import com.youngman.mopbatch.infra.fcm.service.ChairFcmTokenFetchService;
import com.youngman.mopbatch.infra.fcm.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-25.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mop/notification")
public class NotificationApi {

	private final ChairFcmTokenFetchService chairFcmTokenFetchService;
	private final PushNotificationService pushNotificationService;


	@GetMapping
	public ResponseEntity<?> pushNotification() {
		List<String> fcmTokens = chairFcmTokenFetchService.fetchChairFcmTokenByStatisticsDate();
		FirebaseResponse firebaseResponse = pushNotificationService.sendNotification();

		return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	}

}
