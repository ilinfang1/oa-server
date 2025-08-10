package org.oaoa.demo.service.security;

import org.oaoa.demo.common.currentuser.CurrentUser;
import org.oaoa.demo.vo.MenuVo;

import java.util.List;
import java.util.Map;

public interface HomeService {
    List<MenuVo> getMenuList(CurrentUser currentUser);

    void updatePassword(Map<String, String> pwdDto);
}
