/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.exceptions;

/**
 *
 * @author 1003
 */
public class InvalidUserException extends Exception{
    private static final long serialVersionUID = 5455102821775407290L;

    public InvalidUserException(String msg) {
        super(msg);
    }
    
}
