package org.oaoa.demo.service.security.Impl;

import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.currentuser.CurrentUser;
import org.oaoa.demo.common.ex.BusinessException;
import org.oaoa.demo.dao.LoginDao;
import org.oaoa.demo.service.security.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao;

    @Override
    public CurrentUser checkLogin(Map<String, String> loginDto) {
        Map<String,String> user = loginDao.findUserByUserIdAndUserPwd(loginDto);
        if(user == null){
            throw new BusinessException("账号或密码错误！");
        }
        CurrentUser currentUser = new CurrentUser(user.get("u_id"),user.get("u_name"));
        return currentUser;
    }
}
