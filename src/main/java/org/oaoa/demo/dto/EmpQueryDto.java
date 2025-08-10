package org.oaoa.demo.dto;

import lombok.Data;
import org.oaoa.demo.common.page.PageParamDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EmpQueryDto  extends PageParamDto {
    private String e_id;
    private String e_name;
    private Integer e_sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date e_birth_start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date e_birth_end;
    private Integer e_status;
    private Integer d_id;
}
