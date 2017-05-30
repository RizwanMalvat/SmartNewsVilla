package com.smartnewsvillaadmin.serviceImpl;

import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.entities.Blogs;
import com.smartnewsvillaadmin.repositories.AdminAuthRepository;
import com.smartnewsvillaadmin.repositories.BlogsRepository;
import com.smartnewsvillaadmin.repositories.BlogsRepository;
import com.smartnewsvillaadmin.services.AdminAuthService;
import com.smartnewsvillaadmin.services.BlogsService;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogsServiceImpl implements BlogsService {

    @Resource
    private BlogsRepository blogsRepository;

    @Override
    public void save(Blogs blogs) {
        blogsRepository.save(blogs);
    }

    @Override
    public List<Blogs> findByMenuidMenuidAndMenuidMenutypeAndStatus(Long menuid, String menuType, String status) {
        return blogsRepository.findByMenuidFirstmenuidAndMenuidMenutypeAndStatus(menuid, menuType, status);
    }

    @Override
    public Blogs findByBlogidAndStatus(Long blogid, String status) {
        return blogsRepository.findByBlogidAndStatus(blogid, status);
    }
}
