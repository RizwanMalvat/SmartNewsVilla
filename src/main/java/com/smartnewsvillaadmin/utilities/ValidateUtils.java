package com.smartnewsvillaadmin.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ValidateUtils {

    public ValidateUtils() {
    }

    public void checkNull(HttpServletRequest request, String fieldname, String displayfield, List<String> errors) {
        if (request.getParameter(fieldname) == null || request.getParameter(fieldname).trim().equals("")) {
            errors.add(displayfield + " is required.");
        }
    }

    public void checkNull(HttpServletRequest request, String fieldname, String displayfield, HashMap<String, String> errors) {
        if (request.getParameter(fieldname) == null || request.getParameter(fieldname).trim().equals("")) {
            errors.put(fieldname, displayfield + " is required.");
        }
    }

    public void checkMultiValueNull(HttpServletRequest request, String fieldname, String displayfield, List<String> errors) {
        if (request.getParameter(fieldname) == null || request.getParameterValues(fieldname).length == 0) {
            errors.add(displayfield + " is required.");
        }
    }

    public void checkLength(HttpServletRequest request, String fieldname, String displayfield, int size,
            List<String> errors) {
        if (request.getParameter(fieldname) == null || request.getParameter(fieldname).trim().equals("")) {

        } else if (request.getParameter(fieldname).length() > size) {
            errors.add("Max number of characters allowed for " + displayfield + " is " + size + ".");
        }
    }

    public void checkLength(HttpServletRequest request, String fieldname, String displayfield, int size, HashMap<String, String> errors) {
        if (request.getParameter(fieldname) == null || request.getParameter(fieldname).trim().equals("")) {

        } else if (request.getParameter(fieldname).length() > size) {
            errors.put(fieldname, "Max number of characters allowed for " + displayfield + " is " + size + ".");
        }
    }

    public boolean isValidEmailAddress(String email) {

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValidPassword(String password) {

        String ePattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(password);
        return m.matches();
    }

    public boolean isURL(String url) {
        if (url == null) {
            return false;
        }
        String urlRegex = "\\b(https?|ftp|file|ldap)://"
                + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]"
                + "*[-A-Za-z0-9+&@#/%=~_|]";
        return url.trim().matches(urlRegex);
    }

    public boolean isBigint(HttpServletRequest request, String fieldname, String displayfield, HashMap<String, String> errors) {
        try {
            new BigInteger(request.getParameter(fieldname));
            return true;
        } catch (Exception e) {
            errors.put(fieldname, displayfield + " would be in number format.");
            return false;
        }
    }

    public boolean isBigint(HttpServletRequest request, String fieldname, String displayfield, List<String> errors) {
        try {
            new BigInteger(request.getParameter(fieldname));
            return true;
        } catch (Exception e) {
            errors.add(displayfield + " would be in number format.");
            return false;
        }
    }

    public boolean isLong(HttpServletRequest request, String fieldname, String displayfield, List<String> errors) {
        try {
            Long.parseLong(request.getParameter(fieldname));
            return true;
        } catch (Exception e) {
            errors.add(displayfield + " would be in number format.");
            return false;
        }
    }

    public boolean isDouble(HttpServletRequest request, String fieldname, String displayfield, List<String> errors) {
        try {
            Double.valueOf(request.getParameter(fieldname));
            return true;
        } catch (Exception e) {
            errors.add(displayfield + " would be in number format.");
            return false;
        }
    }

    public boolean isBigdecimal(HttpServletRequest request, String fieldname, String displayfield, HashMap<String, String> errors) {
        try {
            new BigDecimal(request.getParameter(fieldname));
            return true;
        } catch (Exception e) {
            errors.put(fieldname, displayfield + " would be in number format.");
            return false;
        }
    }

    public boolean isBigdecimal(HttpServletRequest request, String fieldname, String displayfield, List<String> errors) {
        try {
            new BigDecimal(request.getParameter(fieldname).trim());
            return true;
        } catch (Exception e) {
            errors.add(displayfield + " would be in number format.");
            return false;
        }
    }
}
