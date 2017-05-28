package com.smartnewsvillaadmin.serviceImpl;

import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.repositories.AdminAuthRepository;
import com.smartnewsvillaadmin.services.AdminAuthService;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminAuthServiceImpl implements AdminAuthService {

    @Resource
    private AdminAuthRepository adminAuthRepository;

    @Override
    public List<AdminAuth> findAll(String accountstatus, String type) {
        return adminAuthRepository.findByStatusAndType(accountstatus, type);
    }

    @Override
    public AdminAuth findByUserNameAndPassword(String username, String Password, String status, List<String> type) {
        return adminAuthRepository.findTopByUsernameAndPasswordAndStatusAndTypeIn(username, Password, status, type);
    }

    @Override
    public AdminAuth findTopByAuthidAndStatusNotIn(Long accountid, String status) {
        return adminAuthRepository.findTopByAuthidAndStatus(accountid, status);
    }

    @Override
    public AdminAuth findByUsernameAndStatusIn(String username, List<String> status) {
        return adminAuthRepository.findByUsernameAndStatusIn(username, status);
    }

    @Override
    public void save(AdminAuth adminAuth) {
        adminAuthRepository.save(adminAuth);
    }

    @Override
    public AdminAuth findByUsernameAndStatusInOrEmail(String username, String email, List<String> status) {
        return adminAuthRepository.findByUsernameAndStatusInAndEmail(username, email, status);
    }

    @Override
    public AdminAuth findByUserNameAndPasswordOrEmailAndPassword(String username, String email, String Password, String status) {
        return adminAuthRepository.findByUserNameAndPasswordOrEmailAndPassword(username, email, Password, status);
    }

}
