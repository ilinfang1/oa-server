package org.oaoa.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.oaoa.demo.common.LeaveStatusEnum;

import java.util.Date;

@Data
public class Leave {
    private Long l_id;
    private String e_id;
    private String e_name;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date l_submit_date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date l_start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date l_end;
    private String l_cause;
    private Integer l_status;

    private String getL_status_name() {
        if(l_status == null) return null;
        return LeaveStatusEnum.getText(l_status);
    }
    private String d_name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date l_sp_date;
    private String l_sp_advice;
    private String l_spr;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date l_back_date;
}
