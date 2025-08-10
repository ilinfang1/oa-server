package org.oaoa.demo.service.info.Impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.InfoStatusEnum;
import org.oaoa.demo.common.ex.BusinessException;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.common.page.QueryAction;
import org.oaoa.demo.dao.DepDao;
import org.oaoa.demo.dto.DepDto;
import org.oaoa.demo.dto.DepQueryDto;
import org.oaoa.demo.model.Dep;
import org.oaoa.demo.service.info.DepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DepServiceImpl implements DepService {
    private final DepDao depDao;

    @Override
    public PageVo<Dep> getDepPage(DepQueryDto depQueryDto) {

//        QueryAction<Dep> action = new QueryAction<Dep>() {
//            @Override
//            public List<Dep> executeQuery() throws Exception {
//                return depDao.findDepList(depQueryDto);
//            }
//        };// 匿名内部类

//        QueryAction<Dep> action = () -> depDao.findDepList(depQueryDto);// lambda

        return PageVo.getPageVo(depQueryDto, () -> depDao.findDepList(depQueryDto));// lambda
    }

    private int id;//最大编号

    @PostConstruct//(初始化方法) 构造方法后执行, 只执行一次
    public void init() {
        id = depDao.findMaxId();

    }

    private synchronized int newId() {
        return ++id;
    }// 同步方法, 保证线程安全

    @Override
    public void addDep(DepDto depDto) {
        depDto.setD_id(newId());// 设置部门编号
        depDto.setD_status(InfoStatusEnum.undetermined.getCode());
        depDao.insertDep(depDto);// 调用DAO层的插入方法
    }

    @Override
    public void updateDep(DepDto depDto) {
        depDao.updateDep(depDto);
    }

    @Override
    public void removeDep(Integer... dIds) {
        if (dIds == null || dIds.length == 0) {
            throw new BusinessException("请选择要删除的部门");

        }
        // 如果被删除的数据中存在的状态不是“未确定”的，则抛出异常并阻止
        boolean exists = depDao.findExistsUndetermined(dIds);
        if (exists) {
            throw new BusinessException("存在状态“已确定”的部门，请重新选择");
        }
        depDao.deleteDep(dIds);// 调用DAO层的删除方法
    }

    @Override
    public void changeStatus(Integer id, Integer status) {
        depDao.changeStatus(id, status);
    }
}
