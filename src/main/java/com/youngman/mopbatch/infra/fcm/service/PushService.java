package com.youngman.mopbatch.infra.fcm.service;

import com.youngman.mopbatch.domain.member.dao.MemberFindDao;
import com.youngman.mopbatch.domain.member.dao.MemberRepository;
import com.youngman.mopbatch.global.interceptor.HeaderRequestInterceptor;
import com.youngman.mopbatch.infra.fcm.constant.EmergencyMessage;
import com.youngman.mopbatch.infra.fcm.constant.FcmConstant;
import com.youngman.mopbatch.infra.fcm.constant.StatisticsMessage;
import com.youngman.mopbatch.infra.fcm.dto.FirebaseResponse;
import com.youngman.mopbatch.infra.fcm.dto.NotificationResponse;
import com.youngman.mopbatch.infra.fcm.dto.PushResponse;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PushService {

    private final MemberFindDao memberFindDao;

    public FirebaseResponse sendNotification(List<String> fcmTokens, String sender) {
        String senderName = memberFindDao.findNameByEmail(sender);

        HttpEntity<PushResponse> request = new HttpEntity<>(getPushResponse(fcmTokens, senderName));
        CompletableFuture<FirebaseResponse> pushNotification = this.send(request);
        CompletableFuture.allOf(pushNotification).join();

        FirebaseResponse firebaseResponse = null;

        try {
            firebaseResponse = pushNotification.get();
        } catch (InterruptedException | ExecutionException e) {
            log.info("ERROR => {}", e.getMessage());
        }

        return firebaseResponse;
    }

    private PushResponse getPushResponse(List<String> fcmTokens, String sender) {
        String body = (sender != null) ? sender + EmergencyMessage.BODY : StatisticsMessage.BODY;
        NotificationResponse notificationResponse = NotificationResponse.builder()
                .body(body)
                .build();

        return PushResponse.builder()
                .notificationResponse(notificationResponse)
                .registrationIds(fcmTokens)
                .build();
    }

    @Async
    public CompletableFuture<FirebaseResponse> send(HttpEntity<PushResponse> entity) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(makeFcmInterceptors());
        FirebaseResponse firebaseResponse = restTemplate.postForObject(FcmConstant.URL, entity, FirebaseResponse.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

    private ArrayList<ClientHttpRequestInterceptor> makeFcmInterceptors() {
        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FcmConstant.KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));

        return interceptors;
    }
}
