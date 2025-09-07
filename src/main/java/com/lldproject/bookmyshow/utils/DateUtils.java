package com.lldproject.bookmyshow.utils;

import java.util.Date;

public class DateUtils {
    public static Boolean isValidShowtimings(Date startTime, Date endTime) {

        Date now=new Date();
        if(startTime==null||endTime==null) {
            return false;
        }

        if(startTime.before(now)|| endTime.before(startTime)) {
            return false;
        }

        return true;

    }
}
