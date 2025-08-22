package org.oaoa.demo.service.leave.Impl;

import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.LeaveStatusEnum;
import org.oaoa.demo.common.ex.BusinessException;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dao.ApprevDao;
import org.oaoa.demo.dto.LeaveDto;
import org.oaoa.demo.dto.LeaveQueryDto;
import org.oaoa.demo.model.Leave;
import org.oaoa.demo.service.leave.ApprevService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApprevServiceImpl implements ApprevService {
    private final ApprevDao apprevDao;

    @Override
    public PageVo<Leave> getApprevList(LeaveQueryDto dto) {
        return PageVo.getPageVo(dto,()->apprevDao.findApprevList(dto));
    }

    @Override
    public void addLeave(LeaveDto leaveDto) {
        apprevDao.insertLeave(leaveDto);
    }

    @Override
    public void updateLeave(LeaveDto leaveDto) {
        apprevDao.updateLeave(leaveDto);
    }

    @Override
    public void removeLeave(Long... ids) {
        if(ids==null || ids.length==0){
            throw new RuntimeException("请至少选择一条请假申请！！");
        }

        //如果有提交后的数据，不允许删除
        boolean exists =apprevDao.findExistsSubmitted(ids);
        if(exists){
            throw new BusinessException("存在已提交请假申请，不允许删除！");
        }
        apprevDao.deleteLeave(ids);
     }

    @Override
    public void submitLeave(Long id) {
        apprevDao.updateLeaveStatus(id, LeaveStatusEnum.SUBMITED.getCode());
    }

    @Override
    public void backLeave(Long id) {
        apprevDao.backLeave(id, LeaveStatusEnum.REVERSE.getCode());
    }
}
