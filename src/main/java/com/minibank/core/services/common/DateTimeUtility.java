package com.minibank.core.services.common;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ann on 13/09/14.
 */
public class DateTimeUtility
{
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final Time MIN_TIME = Time.valueOf("00:00:00");
    public static final Time MAX_TIME = Time.valueOf("23:59:59");

    public static java.sql.Date getSqlDate(Date utilDate)
    {

        SimpleDateFormat ft = new SimpleDateFormat(DATE_FORMAT);
        String output = ft.format(utilDate);
        return java.sql.Date.valueOf(output);
    }

    public static java.sql.Time getSqlTime(Date utilDate)
    {

        SimpleDateFormat ft = new SimpleDateFormat(TIME_FORMAT);
        String output = ft.format(utilDate);
        return Time.valueOf(output);
    }

    public static Time increaseTime(Time fromTime, Integer hours)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        Calendar c = Calendar.getInstance();
        c.setTime(fromTime);
        c.add(Calendar.HOUR_OF_DAY, hours);
        String output = sdf.format(c.getTime());
        return Time.valueOf(output);
    }
}
