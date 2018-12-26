package com.krista.java.test.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateFormatWrapperWithLock
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/26 11:01
 */
public class DateFormatWrapperWithLock {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static synchronized String format(Date date) {
        return SDF.format(date);
    }

    public static synchronized  Date parse(String str) throws ParseException {
        return SDF.parse(str);
    }
}
