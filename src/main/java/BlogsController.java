/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.smartnewsvillaadmin.BusinessService.BlogsBusinessesService;
import com.smartnewsvillaadmin.controllers.*;
import com.smartnewsvillaadmin.BusinessService.MenuBusinessesService;
import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.entities.Blogs;
import com.smartnewsvillaadmin.entities.FirstLevelMenu;
import com.smartnewsvillaadmin.exceptions.InvalidUserException;
import com.smartnewsvillaadmin.services.BlogsService;
import com.smartnewsvillaadmin.services.FirstLevelMenuService;
import com.smartnewsvillaadmin.utilities.Constant;
import com.smartnewsvillaadmin.utilities.SessionUtils;
import com.smartnewsvillaadmin.utilities.ValidateUtils;
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
    public String list(Model model, HttpServletRequest request,@PathVariable("menuid") Long menuid) {
        System.out.println("Inside contact");
        model.addAttribute("blogs", blogsService.findByMenuidMenuidAndStatus(menuid, Constant.ACTIVE));
        return "Blogs/first_level_blog";
    }

    @RequestMapping(value = "firstlevelmenu/addblog", method = RequestMethod.POST)
    public String addfirstlevelmenu(Model model, HttpServletRequest request) {
        System.out.println("Inside addfirstlevelmenu");
        AdminAuth adminAuth = (AdminAuth) sessionUtils.getSessionValue(request, Constant.ADMINUSERS);
        List<String> errors = new ArrayList();
        BlogsBusinessesService blogsBusinessesService = new BlogsBusinessesService();
        blogsBusinessesService.validate(request, errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", firstLevelMenuService.findAllByMenutypeStatus(Constant.FIRSTLEVEL, Constant.ACTIVE));
            return "Blogs/first_level_blog";
        }
        if (firstLevelMenuService.findExistingMenuNameOrMenuPathByMenuTypeAndStatus(request.getParameter("menuname"), request.getParameter("menupath"), Constant.FIRSTLEVEL, Constant.ACTIVE)) {
            errors.add("Menu Name or Menu path exists for this Menu Type");
            model.addAttribute("errors", errors);
            model.addAttribute("blogs", firstLevelMenuService.findAllByMenutypeStatus(Constant.FIRSTLEVEL, Constant.ACTIVE));
            return "Blogs/first_level_blog";
        }
        Blogs blogs = new Blogs();
        blogsBusinessesService.setFields(blogs, request);
        blogs.setCreatedby(adminAuth.getEmail());
        blogs.setCreateddate(new Date());
        blogsService.save(blogs);
        return "redirect:/Blogs/?m=c";
    }

    @RequestMapping(value = "/secondmenu/{parentmenuid}", method = RequestMethod.GET)
    public String secondmenu(Model model, HttpServletRequest request, @PathVariable("parentmenuid") Long parentmenuid) {
        System.out.println("Inside contact");
        model.addAttribute("parentmenuid", parentmenuid);
        model.addAttribute("firstlevelmenu", firstLevelMenuService.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(Constant.SECONDLEVEL, parentmenuid, Constant.ACTIVE));
        return "Menus/second_level_menu";
    }

    @RequestMapping(value = "secondlevelmenu/addmenu/{parentmenuid}", method = RequestMethod.POST)
    public String secondlevelmenu(Model model, HttpServletRequest request, @PathVariable("parentmenuid") Long parentmenuid) {
        System.out.println("Inside addfirstlevelmenu");
        AdminAuth adminAuth = (AdminAuth) sessionUtils.getSessionValue(request, Constant.ADMINUSERS);
        List<String> errors = new ArrayList();
        MenuBusinessesService menuBusinessesService = new MenuBusinessesService();
        menuBusinessesService.validate(request, errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("parentmenuid", parentmenuid);
            model.addAttribute("firstlevelmenu", firstLevelMenuService.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(Constant.SECONDLEVEL, parentmenuid, Constant.ACTIVE));
            return "Menus/second_level_menu";
        }
        if (firstLevelMenuService.findExistingMenuNameOrMenuPathByMenuTypeAndStatus(request.getParameter("menuname"), request.getParameter("menupath"), Constant.SECONDLEVEL, Constant.ACTIVE)) {
            errors.add("Menu Name or Menu path exists for this Menu Type");
            model.addAttribute("errors", errors);
            model.addAttribute("parentmenuid", parentmenuid);
            model.addAttribute("firstlevelmenu", firstLevelMenuService.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(Constant.SECONDLEVEL, parentmenuid, Constant.ACTIVE));
            return "Menus/second_level_menu";
        }
        FirstLevelMenu levelMenu = firstLevelMenuService.findTopByFirstmenuidAndMenutypeAndStatus(parentmenuid, Constant.FIRSTLEVEL, Constant.ACTIVE);
        if (levelMenu == null) {
            errors.add("Provide proper parentmenuid");
            model.addAttribute("errors", errors);
            model.addAttribute("parentmenuid", parentmenuid);
            model.addAttribute("firstlevelmenu", firstLevelMenuService.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(Constant.SECONDLEVEL, parentmenuid, Constant.ACTIVE));
            return "Menus/second_level_menu";
        }
        FirstLevelMenu firstLevelMenu = new FirstLevelMenu();
        menuBusinessesService.setFields(firstLevelMenu, request);
        firstLevelMenu.setCreatedby(adminAuth.getEmail());
        firstLevelMenu.setCreateddate(new Date());
        firstLevelMenu.setMenutype(Constant.SECONDLEVEL);
        firstLevelMenu.setParentmenuid(levelMenu);
        firstLevelMenuService.save(firstLevelMenu);
        return "redirect:/Menus/secondmenu/"+ parentmenuid +"?m=c";
    }

    @RequestMapping(value = "/thirdmenu/{parentmenuid}", method = RequestMethod.GET)
    public String thirdmenu(Model model, HttpServletRequest request, @PathVariable("parentmenuid") Long parentmenuid) {
        System.out.println("Inside contact");
        model.addAttribute("parentmenuid", parentmenuid);
        model.addAttribute("firstlevelmenu", firstLevelMenuService.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(Constant.THIRDLEVEL, parentmenuid, Constant.ACTIVE));
        return "Menus/third_level_menu";
    }

    @RequestMapping(value = "thirdlevelmenu/addmenu/{parentmenuid}", method = RequestMethod.POST)
    public String thirdlevelmenu(Model model, HttpServletRequest request, @PathVariable("parentmenuid") Long parentmenuid) {
        System.out.println("Inside addfirstlevelmenu");
        AdminAuth adminAuth = (AdminAuth) sessionUtils.getSessionValue(request, Constant.ADMINUSERS);
        List<String> errors = new ArrayList();
        MenuBusinessesService menuBusinessesService = new MenuBusinessesService();
        menuBusinessesService.validate(request, errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("parentmenuid", parentmenuid);
            model.addAttribute("firstlevelmenu", firstLevelMenuService.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(Constant.THIRDLEVEL, parentmenuid, Constant.ACTIVE));
            return "Menus/third_level_menu";
        }
        if (firstLevelMenuService.findExistingMenuNameOrMenuPathByMenuTypeAndStatus(request.getParameter("menuname"), request.getParameter("menupath"), Constant.SECONDLEVEL, Constant.ACTIVE)) {
            errors.add("Menu Name or Menu path exists for this Menu Type");
            model.addAttribute("errors", errors);
            model.addAttribute("parentmenuid", parentmenuid);
            model.addAttribute("firstlevelmenu", firstLevelMenuService.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(Constant.THIRDLEVEL, parentmenuid, Constant.ACTIVE));
            return "Menus/third_level_menu";
        }
        FirstLevelMenu levelMenu = firstLevelMenuService.findTopByFirstmenuidAndMenutypeAndStatus(parentmenuid, Constant.SECONDLEVEL, Constant.ACTIVE);
        if (levelMenu == null) {
            errors.add("Provide proper parentmenuid");
            model.addAttribute("errors", errors);
            model.addAttribute("parentmenuid", parentmenuid);
            model.addAttribute("firstlevelmenu", firstLevelMenuService.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(Constant.THIRDLEVEL, parentmenuid, Constant.ACTIVE));
            return "Menus/third_level_menu";
        }
        FirstLevelMenu firstLevelMenu = new FirstLevelMenu();
        menuBusinessesService.setFields(firstLevelMenu, request);
        firstLevelMenu.setCreatedby(adminAuth.getEmail());
        firstLevelMenu.setCreateddate(new Date());
        firstLevelMenu.setMenutype(Constant.THIRDLEVEL);
        firstLevelMenu.setParentmenuid(levelMenu);
        firstLevelMenuService.save(firstLevelMenu);
        return "redirect:/Menus/thirdmenu/"+ parentmenuid +"?m=c";
    }

    @ExceptionHandler(InvalidUserException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }
}
