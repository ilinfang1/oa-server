package org.oaoa.demo.api.leave;


import lombok.RequiredArgsConstructor;
import org.oaoa.demo.common.LeaveStatusEnum;
import org.oaoa.demo.common.currentuser.CurrentUser;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.common.vo.R;
import org.oaoa.demo.dto.LeaveDto;
import org.oaoa.demo.dto.LeaveQueryDto;
import org.oaoa.demo.model.Leave;
import org.oaoa.demo.service.leave.ExamService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RequiredArgsConstructor
@RestController
@RequestMapping("/leave/exam")
public class ExamController {
    private final ExamService examService;
    private final CurrentUser currentUser;

    @GetMapping("")
    public R<PageVo<Leave>> examList(LeaveQueryDto dto){
        dto.setLeader_id(currentUser.getUserId());
        PageVo<Leave> page = examService.getExamPage(dto);
        return R.OK(page);
    }

    @PutMapping("/pass")
    public R<?> execPass(@RequestBody LeaveDto leaveDto){
        leaveDto.setL_status(LeaveStatusEnum.PASS.getCode());
        leaveDto.setL_spr(currentUser.getUserName());
        leaveDto.setL_sp_date(new Date());
        examService.spLeave(leaveDto);
        return R.OK();
    }
    @PutMapping("/reject")
    public R<?> execReject(@RequestBody LeaveDto leaveDto){
        leaveDto.setL_status(LeaveStatusEnum.REJECTED.getCode());
        leaveDto.setL_spr(currentUser.getUserName());
        leaveDto.setL_sp_date(new Date());
        examService.spLeave(leaveDto);
        return R.OK();
    }
}
