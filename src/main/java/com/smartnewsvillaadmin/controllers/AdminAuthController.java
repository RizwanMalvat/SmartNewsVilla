/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.controllers;

import com.smartnewsvillaadmin.constants.Status;
import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.services.AdminAuthService;
import com.smartnewsvillaadmin.utilities.Constant;
import com.smartnewsvillaadmin.utilities.EncryptUtils;
import com.smartnewsvillaadmin.utilities.SessionUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 1008
 */
@Scope("request")
@Controller
public class AdminAuthController {

    /**
     *
     */
    @Autowired
    AdminAuthService adminAuthService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        return "Login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        SessionUtils sessionUtils = new SessionUtils();
        if (new SessionUtils().getSessionValue(request, Constant.DEVELOPER) != null) {
            sessionUtils.removeSessionValue(request, Constant.DEVELOPER);
        } else if (new SessionUtils().getSessionValue(request, Constant.BUSINESS) != null) {
            sessionUtils.removeSessionValue(request, Constant.BUSINESS);
        }
//        sessionUtils.invalidate(request);

        return "redirect:/";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model models, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
        if (error != null) {
            models.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (logout != null) {
            models.addAttribute("msg", "You've been logged out successfully.");
        }
        return "Login";
    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
//        if (exception instanceof BadCredentialsException) {
//            error = "Invalid username and password!";
//        } else if (exception instanceof LockedException) {
//            error = exception.getMessage();
//        } else {
//            error = "Invalid username and password!";
//        }

        return error;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String access(Model model, HttpServletRequest request) {
        if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
            model.addAttribute("error", "Email and password are mandatory.");
            return "Login";
        }
        if (request.getParameter("password") == null || request.getParameter("password").equals("")) {
            model.addAttribute("error", "Username and password are mandetory.");
            return "Login";
        }
        AdminAuth maAccounts = adminAuthService.findByUserNameAndPasswordOrEmailAndPassword(request.getParameter("email"), request.getParameter("email"), new EncryptUtils().encodeMD5(request.getParameter("password")), Status.ACTIVE.toString());
//        AdminAuth maAccounts = adminAuthService.findTopByAuthidAndStatusNotIn(1L, Status.ACTIVE.toString());
        System.out.println("Account is " + maAccounts);
        if (maAccounts == null) {
            model.addAttribute("error", "Email/Username or password is incorrect.");
            return "Login";
        }
            new SessionUtils().setSessionValue(request, Constant.ADMINUSERS, maAccounts);
            maAccounts = null;
            return "redirect:/Menus/";
       
    }

}
