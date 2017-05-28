/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.utilities;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author Parag
 */
public class Date extends java.util.Date {

    public Date() {
        super.setTime(getInstance().getTime());
    }

    private java.util.Date getInstance() {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("Asia/Hong_Kong"));
        int hour12 = cal.get(Calendar.YEAR);
        int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0..23
        int minutes = cal.get(Calendar.MINUTE); // 0..59
        int seconds = cal.get(Calendar.SECOND); // 0..59
        Calendar cal1 = new GregorianCalendar();
        cal1.set(hour12, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hour24, minutes, seconds);
        return cal1.getTime();
    }

}
