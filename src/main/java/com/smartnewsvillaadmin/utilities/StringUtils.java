/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.utilities;

/**
 *
 * @author bhavin
 */
public class StringUtils {

    /**
     * 1003
     * Please use this method only in entity.Return "" if str is null.
     * 2.2.2016
     * @param str
     * @return 
     */
    public static String checkNull(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * 1003
     * Please use this method only in entity.Return 0 if str is null.
     * 2.2.2016
     * @param str
     * @return 
     */
    public Long checkNull(Long str) {
        if (str == null) {
            return Long.parseLong("0");
        } else {
            return str;
        }
    }

    /**
     * 
     * @param str
     * @return 
     */
    public static double checkNull(Double str) {
        if (str == null) {
            return Double.parseDouble("0");
        } else {
            return str;
        }
    }

}
