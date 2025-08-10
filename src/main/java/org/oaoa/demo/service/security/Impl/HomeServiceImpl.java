package org.oaoa.demo.service.security.Impl;

import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.currentuser.CurrentUser;
import org.oaoa.demo.common.ex.BusinessException;
import org.oaoa.demo.dao.HomeDao;
import org.oaoa.demo.service.security.HomeService;
import org.oaoa.demo.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeDao homeDao;
    @Override
    public List<MenuVo> getMenuList(CurrentUser currentUser) {
        return homeDao.findMenuList(currentUser);
    }

    @Override
    public void updatePassword(Map<String, String> pwdDto) {
        String factOldPwd =homeDao.findFactOldPwd(pwdDto);
        if(! factOldPwd.equals(pwdDto.get("old_pwd"))){
            throw new BusinessException("旧密码输入错误");
        }
        homeDao.updatePassword(pwdDto);
    }
}
