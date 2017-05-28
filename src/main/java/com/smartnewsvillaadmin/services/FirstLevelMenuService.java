package com.smartnewsvillaadmin.services;

import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.entities.FirstLevelMenu;
import java.util.List;
import org.springframework.context.annotation.Scope;

@Scope(value = "request")
public interface FirstLevelMenuService {

    void save(FirstLevelMenu firstLevelMenu);

    FirstLevelMenu findTopByFirstmenuidAndMenutypeAndStatus(Long firstmenuid,String menutype, String status);

    Boolean findExistingMenuNameOrMenuPathByMenuTypeAndStatus(String menuname, String menupath, String menutype, String status);

    List<FirstLevelMenu> findAllByMenutypeStatus(String menutype,String status);

    List<FirstLevelMenu> findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(String menutype,Long parentmenuid,String status);
}
