package com.smartnewsvillaadmin.BusinessService;

import com.smartnewsvillaadmin.entities.Blogs;
import com.smartnewsvillaadmin.utilities.Constant;
import com.smartnewsvillaadmin.utilities.ValidateUtils;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 1006
 */
public class BlogsBusinessesService {

    public void validate(HttpServletRequest request, List<String> errors) {
        ValidateUtils validateUtils = new ValidateUtils();
        validateUtils.checkNull(request, "blogtitle", "Menu Name", errors);
        validateUtils.checkNull(request, "blogdescription", "Menu Path", errors);
        validateUtils.checkNull(request, "blogdetail", "Menu Path", errors);
    }

    public void setFields(Blogs blogs, HttpServletRequest request){
        blogs.setBlogtitle(request.getParameter("blogtitle"));
        blogs.setBlogdescription(request.getParameter("blogdescription"));
        blogs.setBlogdetails(request.getParameter("blogdetail"));
        blogs.setStatus(Constant.ACTIVE);
    }

}
