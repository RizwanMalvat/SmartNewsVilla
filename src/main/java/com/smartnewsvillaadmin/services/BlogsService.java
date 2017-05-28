package com.smartnewsvillaadmin.services;

import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.entities.Blogs;
import com.smartnewsvillaadmin.utilities.Constant;
import java.util.List;
import org.springframework.context.annotation.Scope;

@Scope(value = "request")
public interface BlogsService {

    void save(Blogs firstLevelMenu);

    List<Blogs> findByMenuidMenuidAndStatus(Long menuid, String status);
}
