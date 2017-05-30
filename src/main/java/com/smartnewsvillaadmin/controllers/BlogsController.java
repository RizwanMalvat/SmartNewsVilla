package com.smartnewsvillaadmin.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.smartnewsvillaadmin.BusinessService.BlogsBusinessesService;
import com.smartnewsvillaadmin.BusinessService.MenuBusinessesService;
import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.entities.Blogs;
import com.smartnewsvillaadmin.entities.FirstLevelMenu;
import com.smartnewsvillaadmin.exceptions.InvalidUserException;
import com.smartnewsvillaadmin.services.BlogsService;
import com.smartnewsvillaadmin.services.FirstLevelMenuService;
import com.smartnewsvillaadmin.utilities.Constant;
import com.smartnewsvillaadmin.utilities.FileUtils;
import com.smartnewsvillaadmin.utilities.FolderUtil;
import com.smartnewsvillaadmin.utilities.SessionUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 1003
 */
@Scope("request")
@Controller
@RequestMapping("/BlogsController")
public class BlogsController {

    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    FirstLevelMenuService firstLevelMenuService;
    @Autowired
    BlogsService blogsService;

    @ModelAttribute("/BlogsController")
    public void checkSession(HttpServletRequest request) throws InvalidUserException {
        System.out.println("Here");
        if (sessionUtils.getSessionValue(request, Constant.ADMINUSERS) == null) {
            throw new InvalidUserException("");
        }
    }

    @RequestMapping(value = "/{menuid}", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, @PathVariable("menuid") Long menuid) {
        System.out.println("Inside contact");
        model.addAttribute("menuid", menuid);
        model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.FIRSTLEVEL, Constant.ACTIVE));
        return "Blogs/first_level_blog";
    }

    @RequestMapping(value = "/view/{blogid}", method = RequestMethod.GET)
    public String view(Model model, HttpServletRequest request, @PathVariable("blogid") Long blogid) {
        System.out.println("Inside contact");
        model.addAttribute("blogs", blogsService.findByBlogidAndStatus(blogid, Constant.ACTIVE));
        return "Blogs/ViewBlog";
    }

    @RequestMapping(value = "firstlevelmenu/addblog/{menuid}", method = RequestMethod.POST)
    public String addfirstlevelmenu(Model model, HttpServletRequest request, @PathVariable("menuid") Long menuid, @RequestParam CommonsMultipartFile[] fileUpload) {
        System.out.println("Inside addfirstlevelmenu");
        AdminAuth adminAuth = (AdminAuth) sessionUtils.getSessionValue(request, Constant.ADMINUSERS);
        List<String> errors = new ArrayList();
        BlogsBusinessesService blogsBusinessesService = new BlogsBusinessesService();
        blogsBusinessesService.validate(request, errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.FIRSTLEVEL, Constant.ACTIVE));
            return "Blogs/first_level_blog";
        }
//        if (firstLevelMenuService.findExistingMenuNameOrMenuPathByMenuTypeAndStatus(request.getParameter("menuname"), request.getParameter("menupath"), Constant.FIRSTLEVEL, Constant.ACTIVE)) {
//            errors.add("Menu Name or Menu path exists for this Menu Type");
//            model.addAttribute("errors", errors);
//            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndStatus(menuid, Constant.ACTIVE));
//            return "Blogs/first_level_blog";
//        }
        FirstLevelMenu firstLevelMenu = firstLevelMenuService.findTopByFirstmenuidAndMenutypeAndStatus(menuid, Constant.FIRSTLEVEL, Constant.ACTIVE);
        if (firstLevelMenu == null) {
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.FIRSTLEVEL, Constant.ACTIVE));
            return "Blogs/first_level_blog";
        }
        Blogs blogs = new Blogs();
        try {
            if (fileUpload != null && fileUpload.length > 0) {
                for (CommonsMultipartFile aFile : fileUpload) {
                    if (!aFile.getOriginalFilename().equals("")) {
                        if (!(aFile.getOriginalFilename().toLowerCase().endsWith(".jpg") || aFile.getOriginalFilename().toLowerCase().endsWith(".jpeg") || aFile.getOriginalFilename().toLowerCase().endsWith(".png") || aFile.getOriginalFilename().toLowerCase().endsWith(".bitmap") || aFile.getOriginalFilename().toLowerCase().endsWith(".gif"))) {
                            errors.add(" invalid file type .Allowed extensions : .jpg,.jpeg,.png,.bitmap,.gif");
                        } else {
                            String filename = new FileUtils().rename("BlogImage", aFile.getOriginalFilename(), adminAuth);
                            aFile.transferTo(new File(new FolderUtil().getPublicProfilePath() + filename));
                            blogs.setBlogimage(filename);
                        }
                    } else {
                        errors.add("Select an image");
                    }
                }
            } else {
                errors.add("Select an image");
            }

            if (!errors.isEmpty()) {
                model.addAttribute("errors", errors);
                model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.FIRSTLEVEL, Constant.ACTIVE));
                return "Blogs/first_level_blog";
            }
            blogsBusinessesService.setFields(blogs, request);
            blogs.setCreatedby(adminAuth.getEmail());
            blogs.setCreateddate(new Date());
            blogs.setMenuid(firstLevelMenu);
            blogsService.save(blogs);
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("There is some problem. Please try again after some time");
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.FIRSTLEVEL, Constant.ACTIVE));
            return "Blogs/first_level_blog";
        }
        return "redirect:/BlogsController/" + menuid + "?m=c";
    }

    @RequestMapping(value = "/secondmenu/{menuid}", method = RequestMethod.GET)
    public String secondmenu(Model model, HttpServletRequest request, @PathVariable("menuid") Long menuid) {
        System.out.println("Inside contact");
        model.addAttribute("menuid", menuid);
        model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.SECONDLEVEL, Constant.ACTIVE));
        return "Blogs/second_level_blog";
    }

    @RequestMapping(value = "secondlevelmenu/addblog/{menuid}", method = RequestMethod.POST)
    public String secondlevelmenu(Model model, HttpServletRequest request, @PathVariable("menuid") Long menuid, @RequestParam CommonsMultipartFile[] fileUpload) {
        System.out.println("Inside addfirstlevelmenu");
        AdminAuth adminAuth = (AdminAuth) sessionUtils.getSessionValue(request, Constant.ADMINUSERS);
        List<String> errors = new ArrayList();
        BlogsBusinessesService blogsBusinessesService = new BlogsBusinessesService();
        blogsBusinessesService.validate(request, errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.SECONDLEVEL, Constant.ACTIVE));
            return "Blogs/second_level_blog";
        }
//        if (firstLevelMenuService.findExistingMenuNameOrMenuPathByMenuTypeAndStatus(request.getParameter("menuname"), request.getParameter("menupath"), Constant.FIRSTLEVEL, Constant.ACTIVE)) {
//            errors.add("Menu Name or Menu path exists for this Menu Type");
//            model.addAttribute("errors", errors);
//            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndStatus(menuid, Constant.ACTIVE));
//            return "Blogs/second_level_blog";
//        }
        FirstLevelMenu firstLevelMenu = firstLevelMenuService.findTopByFirstmenuidAndMenutypeAndStatus(menuid, Constant.SECONDLEVEL, Constant.ACTIVE);
        if (firstLevelMenu == null) {
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.SECONDLEVEL, Constant.ACTIVE));
            return "Blogs/second_level_blog";
        }
        Blogs blogs = new Blogs();
        try {
            if (fileUpload != null && fileUpload.length > 0) {
                for (CommonsMultipartFile aFile : fileUpload) {
                    if (!aFile.getOriginalFilename().equals("")) {
                        if (!(aFile.getOriginalFilename().toLowerCase().endsWith(".jpg") || aFile.getOriginalFilename().toLowerCase().endsWith(".jpeg") || aFile.getOriginalFilename().toLowerCase().endsWith(".png") || aFile.getOriginalFilename().toLowerCase().endsWith(".bitmap") || aFile.getOriginalFilename().toLowerCase().endsWith(".gif"))) {
                            errors.add(" invalid file type .Allowed extensions : .jpg,.jpeg,.png,.bitmap,.gif");
                        } else {
                            String filename = new FileUtils().rename("BlogImage", aFile.getOriginalFilename(), adminAuth);
                            aFile.transferTo(new File(new FolderUtil().getPublicProfilePath() + filename));
                            blogs.setBlogimage(filename);
                        }
                    } else {
                        errors.add("Select an image");
                    }
                }
            } else {
                errors.add("Select an image");
            }

            if (!errors.isEmpty()) {
                model.addAttribute("errors", errors);
                model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.SECONDLEVEL, Constant.ACTIVE));
                return "Blogs/second_level_blog";
            }
            blogsBusinessesService.setFields(blogs, request);
            blogs.setCreatedby(adminAuth.getEmail());
            blogs.setCreateddate(new Date());
            blogs.setMenuid(firstLevelMenu);
            blogsService.save(blogs);
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("There is some problem. Please try again after some time");
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.SECONDLEVEL, Constant.ACTIVE));
            return "Blogs/second_level_blog";
        }
        return "redirect:/BlogsController/secondmenu/" + menuid + "?m=c";
    }

    @RequestMapping(value = "/thirdmenu/{menuid}", method = RequestMethod.GET)
    public String thirdmenu(Model model, HttpServletRequest request, @PathVariable("menuid") Long menuid) {
        System.out.println("Inside contact");
        model.addAttribute("menuid", menuid);
        model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.THIRDLEVEL, Constant.ACTIVE));
        return "Blogs/third_level_blog";
    }

    @RequestMapping(value = "thirdlevelmenu/addblog/{menuid}", method = RequestMethod.POST)
    public String thirdlevelmenu(Model model, HttpServletRequest request, @PathVariable("menuid") Long menuid, @RequestParam CommonsMultipartFile[] fileUpload) {
        System.out.println("Inside addfirstlevelmenu");
        AdminAuth adminAuth = (AdminAuth) sessionUtils.getSessionValue(request, Constant.ADMINUSERS);
        List<String> errors = new ArrayList();
        BlogsBusinessesService blogsBusinessesService = new BlogsBusinessesService();
        blogsBusinessesService.validate(request, errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.THIRDLEVEL, Constant.ACTIVE));
            return "Blogs/third_level_blog";
        }
//        if (firstLevelMenuService.findExistingMenuNameOrMenuPathByMenuTypeAndStatus(request.getParameter("menuname"), request.getParameter("menupath"), Constant.FIRSTLEVEL, Constant.ACTIVE)) {
//            errors.add("Menu Name or Menu path exists for this Menu Type");
//            model.addAttribute("errors", errors);
//            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndStatus(menuid, Constant.ACTIVE));
//            return "Blogs/third_level_blog";
//        }
        FirstLevelMenu firstLevelMenu = firstLevelMenuService.findTopByFirstmenuidAndMenutypeAndStatus(menuid, Constant.THIRDLEVEL, Constant.ACTIVE);
        if (firstLevelMenu == null) {
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.THIRDLEVEL, Constant.ACTIVE));
            return "Blogs/third_level_blog";
        }
        Blogs blogs = new Blogs();
        try {
            if (fileUpload != null && fileUpload.length > 0) {
                for (CommonsMultipartFile aFile : fileUpload) {
                    if (!aFile.getOriginalFilename().equals("")) {
                        if (!(aFile.getOriginalFilename().toLowerCase().endsWith(".jpg") || aFile.getOriginalFilename().toLowerCase().endsWith(".jpeg") || aFile.getOriginalFilename().toLowerCase().endsWith(".png") || aFile.getOriginalFilename().toLowerCase().endsWith(".bitmap") || aFile.getOriginalFilename().toLowerCase().endsWith(".gif"))) {
                            errors.add(" invalid file type .Allowed extensions : .jpg,.jpeg,.png,.bitmap,.gif");
                        } else {
                            String filename = new FileUtils().rename("BlogImage", aFile.getOriginalFilename(), adminAuth);
                            aFile.transferTo(new File(new FolderUtil().getPublicProfilePath() + filename));
                            blogs.setBlogimage(filename);
                        }
                    } else {
                        errors.add("Select an image");
                    }
                }
            } else {
                errors.add("Select an image");
            }

            if (!errors.isEmpty()) {
                model.addAttribute("errors", errors);
                model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.THIRDLEVEL, Constant.ACTIVE));
                return "Blogs/third_level_blog";
            }
            blogsBusinessesService.setFields(blogs, request);
            blogs.setCreatedby(adminAuth.getEmail());
            blogs.setCreateddate(new Date());
            blogs.setMenuid(firstLevelMenu);
            blogsService.save(blogs);
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("There is some problem. Please try again after some time");
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", blogsService.findByMenuidMenuidAndMenuidMenutypeAndStatus(menuid, Constant.THIRDLEVEL, Constant.ACTIVE));
            return "Blogs/third_level_blog";
        }
        return "redirect:/BlogsController/thirdmenu/" + menuid + "?m=c";
    }

    @ExceptionHandler(InvalidUserException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlesError(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        exception.printStackTrace();
        return mav;
    }
}
