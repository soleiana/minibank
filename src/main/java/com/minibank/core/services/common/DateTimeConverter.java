package com.minibank.core.services.common;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ann on 13/09/14.
 */
public class DateTimeConverter
{
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";

    public static java.sql.Date getSqlDate(Date dNow)
    {

        SimpleDateFormat ft = new SimpleDateFormat(DATE_FORMAT);
        String output = ft.format(dNow);
        return java.sql.Date.valueOf(output);
    }

    public static java.sql.Time getSqlTime(Date dNow)
    {

        SimpleDateFormat ft = new SimpleDateFormat(TIME_FORMAT);
        String output = ft.format(dNow);
        return Time.valueOf(output);
    }
}
