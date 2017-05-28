package com.smartnewsvillaadmin.services;

import com.smartnewsvillaadmin.entities.AdminAuth;
import java.util.List;
import org.springframework.context.annotation.Scope;

@Scope(value = "request")
public interface AdminAuthService {

//    Long countAll(String accountstatus);
//
    List<AdminAuth> findAll(String accountstatus, String type);
//
//    void save(LmAccounts accountProfiles);
//
//    LmAccounts findById(Long id, String status);
    AdminAuth findByUsernameAndStatusIn(String username, List<String> status);

    AdminAuth findByUsernameAndStatusInOrEmail(String username, String email, List<String> status);

    AdminAuth findByUserNameAndPassword(String username, String Password, String status, List<String> type);

    AdminAuth findByUserNameAndPasswordOrEmailAndPassword(String username, String email, String Password, String status);

    void save(AdminAuth adminAuth);

//    boolean findByUsernameAndStatus(String username, String status);
//
//    LmAccounts findAccountByUsernameAndStatus(String username, String status);
//
//    //Find Business Admin and Users by businessid.
//    List<LmAccounts> findAdminsAndUsers(String status, List<String> types, long businessid);
//
//    //Find Business Users by businessadminid.
//    List<LmAccounts> findUsers(String status, String type, long businessadminid);
//
    AdminAuth findTopByAuthidAndStatusNotIn(Long accountid, String status);
//
//    public List<LmAccounts> findSuperAdminUsers(String status, List<String> types, long superadminId);
//
//    LmAccounts findUserByBusinessAndAccountid(long businessid, long accountid);
//
//    LmAccounts findUserByBusinessAdminAndAccountid(long businessadminid, long accountid);
//
//    List<LmAccounts> findByCategoryidAndStatus(long categoryid, String status);

}
