package com.krista.java.test.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateFormatWrapperWithThreadLocal
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2018/12/26 11:08
 */
public class DateFormatWrapperWithThreadLocal {
    private static final ThreadLocal<SimpleDateFormat> SDF
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String format(Date date) {
        return SDF.get().format(date);
    }

    public static Date parse(String str) throws ParseException {
        return SDF.get().parse(str);
    }
}
