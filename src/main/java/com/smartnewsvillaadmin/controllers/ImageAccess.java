/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.controllers;

import com.smartnewsvillaadmin.services.AdminAuthService;
import com.smartnewsvillaadmin.utilities.Constant;
import com.smartnewsvillaadmin.utilities.FolderUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope("request")
@RequestMapping(value = "/ImageAccess")
@Controller
public class ImageAccess {
 
    @Autowired
    public AdminAuthService accountService;

    @RequestMapping(value = "/{filename}/{accointid}", method = RequestMethod.GET)
    public void login(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("filename") String filename, @PathVariable("accointid") Long accointid) throws IOException {
        // check sessions for all users

        //if null then check admin account
//        SessionUtils sessionUtils = new SessionUtils();
//        if (sessionUtils.getSessionValue(request, Constant.BUSINESS) == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
//            return;  
//        }

        // Get requested image by path info.
        String documentsPath = new FolderUtil().getPath(accountService.findTopByAuthidAndStatusNotIn(accointid, Constant.ACTIVE));

        // Decode the file name (might contain spaces and on) and prepare file
        // object.
        //File image = new File(documentsPath, URLDecoder.decode(requestedImage, "UTF-8"));
        File image = new File(documentsPath + filename);
        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image,
            // or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Get content type by filename.
        String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(image.getName()); 
        // String contentType = getServletContext().getMimeType(image.getName());

        // Check if file is actually an image  (avoid download of other files by
        // hackers!).
        // For all content types, see:
        // http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image,
            // or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        } 
        // Init servlet response.
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        // Write image content to response.
        Files.copy(image.toPath(), response.getOutputStream());

    }

    @RequestMapping(value = "/public/{filename}/", method = RequestMethod.GET)
    public void publicProfile(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("filename") String filename) throws IOException {
        // check sessions for all users

        //if null then check admin account
//        if (sessionUtils.getSessionValue(request, Constant.ADMIN) == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
//            return;
//        }

        // Get requested image by path info.
        String documentsPath = new FolderUtil().getPublicProfilePath(); 

        // Decode the file name (might contain spaces and on) and prepare file                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
        // object.
        //File image = new File(documentsPath, URLDecoder.decode(requestedImage, "UTF-8"));
        File image = new File(documentsPath + filename);
        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image,
            // or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Get content type by filename.
        String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(image.getName());
        // String contentType = getServletContext().getMimeType(image.getName());

        // Check if file is actually an image (avoid download of other files by
        // hackers!).
        // For all content types, see:
        // http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image,
            // or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        // Init servlet response.
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        // Write image content to response.
        Files.copy(image.toPath(), response.getOutputStream());
    }
}
