package com.smartnewsvillaadmin.serviceImpl;

import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.entities.FirstLevelMenu;
import com.smartnewsvillaadmin.repositories.AdminAuthRepository;
import com.smartnewsvillaadmin.repositories.FirstLevelMenuRepository;
import com.smartnewsvillaadmin.services.AdminAuthService;
import com.smartnewsvillaadmin.services.FirstLevelMenuService;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FirstLevelMenuServiceImpl implements FirstLevelMenuService {

    @Resource
    private FirstLevelMenuRepository firstLevelMenuRepository;

    @Override
    public void save(FirstLevelMenu firstLevelMenu) {
        firstLevelMenuRepository.save(firstLevelMenu);
    }

    @Override
    public FirstLevelMenu findTopByFirstmenuidAndMenutypeAndStatus(Long firstmenuid, String menutype, String status) {
        return firstLevelMenuRepository.findTopByFirstmenuidAndMenutypeAndStatus(firstmenuid, menutype, status);
    }

    @Override
    public Boolean findExistingMenuNameOrMenuPathByMenuTypeAndStatus(String menuname, String menupath, String menutype, String status) {
        if (firstLevelMenuRepository.findExistingMenuNameOrMenuPathByMenuTypeAndStatus(menuname, menupath, menutype, status) != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<FirstLevelMenu> findAllByMenutypeStatus(String menutype, String status) {
        return firstLevelMenuRepository.findAllByMenutypeAndStatus(menutype, status);
    }

    @Override
    public List<FirstLevelMenu> findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(String menutype,Long parentmenuid,String status) {
        return firstLevelMenuRepository.findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(menutype,parentmenuid, status);
    }

}
