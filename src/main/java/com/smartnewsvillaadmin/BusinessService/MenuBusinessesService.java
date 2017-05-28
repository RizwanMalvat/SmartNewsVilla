package com.smartnewsvillaadmin.BusinessService;

import com.smartnewsvillaadmin.entities.FirstLevelMenu;
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
public class MenuBusinessesService {

    public void validate(HttpServletRequest request, List<String> errors) {
        ValidateUtils validateUtils = new ValidateUtils();
        validateUtils.checkNull(request, "menuname", "Menu Name", errors);
        validateUtils.checkNull(request, "menupath", "Menu Path", errors);
    }

    public void setFields(FirstLevelMenu firstLevelMenu, HttpServletRequest request){
        firstLevelMenu.setMenuname(request.getParameter("menuname"));
        firstLevelMenu.setMenupath(request.getParameter("menupath"));
        firstLevelMenu.setStatus(Constant.ACTIVE);
    }

}
