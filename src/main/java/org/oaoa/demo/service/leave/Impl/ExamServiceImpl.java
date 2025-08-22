package org.oaoa.demo.service.leave.Impl;

import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dao.ExamDao;
import org.oaoa.demo.dto.LeaveDto;
import org.oaoa.demo.dto.LeaveQueryDto;
import org.oaoa.demo.model.Leave;
import org.oaoa.demo.service.leave.ExamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {
    private final ExamDao examDao;

    @Override
    public PageVo<Leave> getExamPage(LeaveQueryDto dto) {
        return PageVo.getPageVo(dto, () -> examDao.findExamList(dto));
    }

    @Override
    public void spLeave(LeaveDto leaveDto) {
        examDao.spLeave(leaveDto);
    }
}
