package org.oaoa.demo.service.security.Impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.InfoStatusEnum;
import org.oaoa.demo.common.ex.BusinessException;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dao.RoleDao;
import org.oaoa.demo.dto.RoleDto;
import org.oaoa.demo.dto.RoleQueryDto;
import org.oaoa.demo.model.Role;

import org.oaoa.demo.service.security.RoleService;
import org.oaoa.demo.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Override
    public PageVo<Role> getRolePage(RoleQueryDto roleQueryDto) {

//        QueryAction<Role> action = new QueryAction<Role>() {
//            @Override
//            public List<Role> executeQuery() throws Exception {
//                return roleDao.findRoleList(roleQueryDto);
//            }
//        };// 匿名内部类

//        QueryAction<Role> action = () -> roleDao.findRoleList(roleQueryDto);// lambda

        return PageVo.getPageVo(roleQueryDto, () -> roleDao.findRoleList(roleQueryDto));// lambda
    }

    private int id;//最大编号

    @PostConstruct//(初始化方法) 构造方法后执行, 只执行一次
    public void init() {
        id = roleDao.findMaxId();

    }

    private synchronized int newId() {
        return ++id;
    }// 同步方法, 保证线程安全

    @Override
    public void addRole(RoleDto roleDto) {
        roleDto.setRo_id(newId());// 设置部门编号
        roleDao.insertRole(roleDto);// 调用DAO层的插入方法
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        roleDao.updateRole(roleDto);
    }

    @Override
    public void removeRole(Integer... ids) {
        if (ids == null || ids.length == 0) {
            throw new BusinessException("请选择要删除的部门");

        }

        roleDao.deleteRole(ids);// 调用DAO层的删除方法
    }

    @Override
    public List<MenuVo> getFunList() {
        return roleDao.findAllFunList();
    }

    @Override
    public List<Integer> getRoleFunList(Integer roleId) {
        return roleDao.findRoleFunIdList(roleId);
    }
}
