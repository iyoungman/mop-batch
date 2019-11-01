package com.youngman.mopbatch.domain.clubdailystatistics.api;

import com.youngman.mopbatch.domain.clubdailystatistics.aplication.ClubStatisticsService;
import com.youngman.mopbatch.domain.clubdailystatistics.dto.ClubSignCountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YoungMan on 2019-11-02.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mop/clubstatistics")
public class ClubStatisticsApi {

    private final ClubStatisticsService clubStatisticsService;

    @GetMapping
    public ClubSignCountResponse getClubSignCountResponse(@RequestParam("clubId") Long clubId) {
        return clubStatisticsService.findSignCount(clubId);
    }
}
