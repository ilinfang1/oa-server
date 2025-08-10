package org.oaoa.demo.service.security.Impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.ex.BusinessException;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dao.UserDao;
import org.oaoa.demo.dto.UserDto;
import org.oaoa.demo.dto.UserQueryDto;
import org.oaoa.demo.model.Role;
import org.oaoa.demo.model.User;
import org.oaoa.demo.service.security.UserService;
import org.oaoa.demo.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public PageVo<User> getUserPage(UserQueryDto userQueryDto) {

//        QueryAction<User> action = new QueryAction<User>() {
//            @Override
//            public List<User> executeQuery() throws Exception {
//                return userDao.findUserList(userQueryDto);
//            }
//        };// 匿名内部类

//        QueryAction<User> action = () -> userDao.findUserList(userQueryDto);// lambda

        return PageVo.getPageVo(userQueryDto, () -> userDao.findUserList(userQueryDto));// lambda
    }

    private int id;//最大编号

    @PostConstruct//(初始化方法) 构造方法后执行, 只执行一次
    public void init() {
        id = userDao.findMaxId();

    }

    private synchronized String newId() {
        return String.format("U%05d",++id);
    }// 同步方法, 保证线程安全

    @Override
    public void addUser(UserDto userDto) {
        userDto.setU_id(newId());// 设置部门编号
        userDao.insertUser(userDto);// 调用DAO层的插入方法
    }

    @Override
    public void updateUser(UserDto userDto) {
        userDao.updateUser(userDto);
    }

    @Override
    public void removeUser(String... ids) {
        if (ids == null || ids.length == 0) {
            throw new BusinessException("请选择要删除的部门");

        }

        userDao.deleteUser(ids);// 调用DAO层的删除方法
    }

    @Override
    public List<Role> getRoleList() {
        return userDao.findAllRoleList();
    }

    @Override
    public List<Integer> getUserRoleList(String userId) {
        return userDao.findUserRoleIdList(userId);
    }

    @Override
    public void updateUserRole(Map<String, Object> map) {
        //先删除指定角色下的权限
        userDao.deleteUserRole(map);
        //再添加指定角色下的权限
        userDao.insertUserRole(map);

    }
}
