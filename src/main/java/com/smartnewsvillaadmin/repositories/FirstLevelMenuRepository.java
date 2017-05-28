package com.smartnewsvillaadmin.repositories;

import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.entities.FirstLevelMenu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstLevelMenuRepository extends JpaRepository<FirstLevelMenu, Long> {

    FirstLevelMenu findTopByFirstmenuidAndMenutypeAndStatus(Long firstmenuid,String menutype, String status);

    List<FirstLevelMenu> findAllByMenutypeAndStatus(String menutype,String status);

    List<FirstLevelMenu> findAllByMenutypeAndParentmenuidFirstmenuidAndStatus(String menutype,Long parentmenuid,String status);
    
    @Query(nativeQuery = true,value = "select * from first_level_menu  where menuname  = ?1 and menupath = ?2 and menutype  = ?3 and status = ?4")
    FirstLevelMenu findExistingMenuNameOrMenuPathByMenuTypeAndStatus(String menuname, String menupath, String menutype, String status);
}
