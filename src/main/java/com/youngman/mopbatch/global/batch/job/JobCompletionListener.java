package com.youngman.mopbatch.global.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by YoungMan on 2019-06-25.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class JobCompletionListener extends JobExecutionListenerSupport {

    private final RestTemplate restTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            sendNotificationToClient();
        }
    }

    private void sendNotificationToClient() {
        String requestUrl = "http://13.125.213.79:8090/mop/notification";
        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
    }
}
