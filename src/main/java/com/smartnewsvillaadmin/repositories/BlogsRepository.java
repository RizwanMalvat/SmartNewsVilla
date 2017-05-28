package com.smartnewsvillaadmin.repositories;

import com.smartnewsvillaadmin.entities.Blogs;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, Long> {

    List<Blogs> findByMenuidMenuidAndStatus(Long menuid, String status);
}
