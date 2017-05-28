/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.exceptions;

import java.lang.reflect.Method;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 *
 * @author apple
 */
public class CustomAsyncExceptionHandler  implements AsyncUncaughtExceptionHandler {
 
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        System.out.println("Exception message - " + throwable.getMessage());
        System.out.println("Method name - " + method.getName());
        for (Object param : obj) {
            System.out.println("Parameter value - " + param);
        }
    }
     
}