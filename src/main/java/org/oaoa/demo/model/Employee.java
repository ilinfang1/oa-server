package org.oaoa.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.oaoa.demo.common.InfoStatusEnum;

import java.util.Date;

@Data
public class Employee {
    private String e_id;
    private String e_name;
    private Integer e_sex;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date e_birth;
    private Integer e_status;
    private Integer d_id;
    private String d_name;

    public String getE_status_name() {
        if (e_status == null) return null ;
        return InfoStatusEnum.getTextByCode(e_status);
    }

}
