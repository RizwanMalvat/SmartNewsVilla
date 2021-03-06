package com.smartnewsvillaadmin.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class CheckInput {

    public String checkValue(String value) {
        if (value != null) {
            return value;
        }
        return "";
    }

    public String checkValue(Object value) {
        if (value != null) {
            return value.toString();
        }
        return "";
    }

    public Long checkValue(Long value) {
        if (value != null) {
            return value;
        }
        return Long.valueOf("0");
    }

    public String checkValue(Double value) {
        if (value != null) {
            return value.toString();
        }
        return "";
    }

    public String checkValueEdit(String value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value;
                }
                return valueReq;
            } else {
                return value;
            }
        }
        return "";
    }

    public String checkValueEdit(String value, Object valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value;
                }
                return valueReq.toString();
            } else {
                return value;
            }
        }
        return "";
    }

    public String checkValueEdit(BigDecimal value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(Double value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(Long value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public String checkValueEdit(BigInteger value, String valueReq) {
        if (value != null) {
            if (valueReq != null) {
                if (value.equals(valueReq)) {
                    return value.toString();
                }
                return valueReq;
            } else {
                return value.toString();
            }
        }
        return "";
    }

    public boolean isBigint(String value, List<String> errors) {
        try {
            new BigInteger(value);
            return true;
        } catch (Exception e) {
            errors.add("Family Head Must be in number format");
            return false;
        }
    }
}
