package com.wav.rzpp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: hbw
 **/
public class TimeUtil {
    public synchronized static String TimeStampToTime(Long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
