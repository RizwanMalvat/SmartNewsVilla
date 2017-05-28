/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.utilities;

import com.smartnewsvillaadmin.utilities.Constant;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 1003
 */
public class CommonsUtil {

    public String getIPAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public boolean isDouble(String str) {
        try {
            Double num = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<String> status() {
        List<String> list = new ArrayList<String>();
        list.add(Constant.ACTIVE);
        list.add(Constant.INACTIVE);
        return list;
    }

    public static List<String> businessProductAllStatus() {
        List<String> list = new ArrayList();
        list.add(Constant.ACTIVE);
//        list.add(Constant.INACTIVE);
        list.add(Constant.COMPLETED);
        list.add(Constant.INPROGRESS);
        list.add(Constant.PENDINGCOMPLETED);
        return list;
    }

    public static List<String> developerProductAllStatus() {
        List<String> list = new ArrayList();
        list.add(Constant.ACTIVE);
        list.add(Constant.PENDING);
        list.add(Constant.COMPLETED);
        list.add(Constant.INPROGRESS);
        list.add(Constant.PENDINGCOMPLETED);
        return list;
    }

    public static List<String> formStatus() {
        List<String> list = new ArrayList<String>();
        list.add(Constant.APPROVED);
        list.add(Constant.REJECTED);
        return list;
    }

    public static List<String> activestatus() {
        List<String> list = new ArrayList<String>();
        list.add(Constant.ACTIVE);
        return list;
    }

    public static String getCurrency() {
        return "$";
    }

    public static List<String> inprogressStatus() {
        List<String> list = new ArrayList<String>();
        list.add(Constant.ACTIVE);
        return list;
    }

    public static List<String> adminAccountTypes() {
        List<String> accounttypes = new ArrayList<String>();
        accounttypes.add(Constant.ADMIN);
        accounttypes.add(Constant.ADMINUSERS);
        return accounttypes;
    }

    public static List<String> adminUsersAccountTypes() {
        List<String> accounttypes = new ArrayList<String>();
        accounttypes.add(Constant.ADMINUSERS);
        return accounttypes;
    }

    public static Map<String, String> packagePlanDetails() {
        Map<String, String> packagedetails = new HashMap<String, String>();
        packagedetails.put("1", "5");
        packagedetails.put("5", "20");
        packagedetails.put("10", "35");
        return packagedetails;
    }

    /**
     * Email Regular expression to check whether email is valid or not.
     *
     * @param stremail
     * @return
     */
    public boolean checkEmail(String stremail) {
        Pattern email = Pattern.compile(
                "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
        return email.matcher(stremail).matches();
    }

    public boolean isLong(String str) {
        try {
            Long num = Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isInteger(String str) {
        try {
            Integer num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public BigInteger toBigInteger(String str) {
        try {
            BigInteger num = new BigInteger(str);
            return num;
        } catch (Exception e) {
            return null;
        }
    }

    public BigDecimal toBigDecimal(String str) {
        try {
            BigDecimal num = new BigDecimal(str);
            return num;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isBigdecimal(String str) {
        try {
            BigDecimal num = new BigDecimal(str);
            num = null;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
