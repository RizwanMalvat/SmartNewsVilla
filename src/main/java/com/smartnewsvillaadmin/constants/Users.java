/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.constants;

/**
 *
 * @author Parag
 */
public enum Users {
    ADMIN("Admin"), USER("User");

    private final String value;

    Users(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
