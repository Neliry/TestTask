package com.example.testtask.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class DateUtils {
    public static LocalDateTime getCurrentUtc() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }
}
