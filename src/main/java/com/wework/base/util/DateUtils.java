package com.wework.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static Date stringToDate(String time) throws ParseException {
        if ((time == null) || (time.equals(""))) {
            return null;
        }
        SimpleDateFormat formate = new SimpleDateFormat(DATE_PATTERN);
        return formate.parse(time);
    }

}
