package com.yehor.helpers;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@UtilityClass
public class TimeHelper {

    public static Duration getDurationBetweenNowAnd(ZonedDateTime time) {
        ZonedDateTime currentZoneTime = time.withZoneSameInstant(ZoneId.systemDefault());
        return Duration.between(currentZoneTime, ZonedDateTime.now());
    }

}
