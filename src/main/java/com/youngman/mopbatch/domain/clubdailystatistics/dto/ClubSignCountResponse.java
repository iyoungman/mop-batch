package com.youngman.mopbatch.domain.clubdailystatistics.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-11-02.
 */

@Getter
public class ClubSignCountResponse {

    private List<ClubSignCount> clubSignCounts = new ArrayList<>();

    public ClubSignCountResponse(List<ClubSignCount> clubSignCounts) {
        this.clubSignCounts = clubSignCounts;
    }
}
