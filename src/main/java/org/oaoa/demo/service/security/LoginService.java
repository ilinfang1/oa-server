package org.oaoa.demo.service.security;

import org.oaoa.demo.common.currentuser.CurrentUser;

import java.util.Map;

public interface LoginService {
    CurrentUser checkLogin(Map<String, String> loginDto);

}
