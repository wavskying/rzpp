package com.wav.rzpp.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: hbw
 **/
public class TimeUtil {

    public static Timestamp getCurrentTime() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return timestamp;
    }
}
