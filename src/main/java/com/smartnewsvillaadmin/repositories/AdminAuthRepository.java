package com.smartnewsvillaadmin.repositories;

import com.smartnewsvillaadmin.entities.AdminAuth;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminAuthRepository extends JpaRepository<AdminAuth, Long> {

    List<AdminAuth> findByStatusAndType(String status, String type);

    AdminAuth findTopByUsernameAndPasswordAndStatusAndTypeIn(String username, String password, String status, List<String> type);

    @Query(nativeQuery = true, value = "select * from admin_auth where (username = ?1 and password = ?3 and status = ?4) or (email = ?2 and password = ?3 and status = ?4)")
    AdminAuth findByUserNameAndPasswordOrEmailAndPassword(String username, String email, String password, String status);

    AdminAuth findByUsernameAndStatusIn(String username, List<String> status);

    @Query("select m from AdminAuth m where (m.username = ?1 and m.status in ?3) or (m.email = ?2 and m.status in ?3)")
    AdminAuth findByUsernameAndStatusInAndEmail(String username, String email, List<String> status);

    AdminAuth findTopByAuthidAndStatus(Long accountid, String status);
}
