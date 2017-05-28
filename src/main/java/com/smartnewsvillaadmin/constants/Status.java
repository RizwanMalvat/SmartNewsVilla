/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Parag
 */
public enum Status {
    ACTIVE("Active"), INACTIVE("In Active"), DELETED("Deleted");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> status() {
        return Stream.of(Status.values()).map(Enum::toString).collect(Collectors.toList());
    }
}
