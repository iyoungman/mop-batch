package com.youngman.mopbatch.infra.fcm.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-10-14.
 */

@Getter
public class EmergencyRequest {

    private String sender;
    private List<String> otherEmails = new ArrayList<>();

    public EmergencyRequest(String sender, List<String> otherEmails) {
        this.sender = sender;
        this.otherEmails = otherEmails;
    }
}
