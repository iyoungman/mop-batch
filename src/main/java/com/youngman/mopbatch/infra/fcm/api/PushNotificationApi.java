package com.youngman.mopbatch.infra.fcm.api;

import com.youngman.mopbatch.domain.member.dao.MemberFindDao;
import com.youngman.mopbatch.infra.fcm.dto.EmergencyRequest;
import com.youngman.mopbatch.infra.fcm.dto.FirebaseResponse;
import com.youngman.mopbatch.infra.fcm.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by YoungMan on 2019-06-25.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mop/notification")
public class PushNotificationApi {

    private final ChairFcmTokenFetchService chairFcmTokenFetchService;
    private final MemberFindDao memberFindDao;
    private final PushService pushService;

    @GetMapping
    public ResponseEntity<?> pushStatisticsToChair() {
        List<String> fcmTokens = chairFcmTokenFetchService.fetchChairFcmTokenByStatisticsDate();
        FirebaseResponse firebaseResponse = pushService.sendNotification(fcmTokens, null);

        return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
    }

    @PostMapping("/emergency")
    public ResponseEntity<?> pushEmergencyToOthers(@RequestBody EmergencyRequest emergencyRequest) {
        List<String> fcmTokens = memberFindDao.findFcmTokensByEmails(emergencyRequest.getOtherEmails());
        FirebaseResponse firebaseResponse = pushService.sendNotification(fcmTokens, emergencyRequest.getSender());

        return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
    }

}
