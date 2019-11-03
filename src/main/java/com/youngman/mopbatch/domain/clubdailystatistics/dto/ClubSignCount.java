package com.youngman.mopbatch.domain.clubdailystatistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by YoungMan on 2019-11-02.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubSignCount {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate statisticsDate;
    private String day;
    private long count;

    public ClubSignCount(LocalDate statisticsDate, long count) {
        this.statisticsDate = statisticsDate;
        this.count = count;
    }

    public void convertToDay() {
        this.day = String.valueOf(statisticsDate.getDayOfMonth());
    }

}
