package com.krista.java.test.basic;

import com.krista.extend.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DateTest
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/3/4 18:12
 */
public class DateTest {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = DateTimeUtil.addDate(start, 2);
        System.out.println(DateTimeUtil.format("yyyy-MM-dd HH:mm:ss", start));

        List<String> dateHours = new ArrayList<>();
        Date temp = start;
        while (temp.before(end)) {
            dateHours.add(DateTimeUtil.format("yyyy-MM-dd HH", temp));

            temp = DateTimeUtil.addSecond(temp, 60 * 60);
        }
        dateHours.add(DateTimeUtil.format("yyyy-MM-dd HH", end));

        dateHours.forEach(hour -> System.out.println(hour));

    }
}
