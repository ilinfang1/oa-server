package org.oaoa.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class EmpDto {
    private String e_id;
    private String e_name;
    private Integer e_sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date e_birthday;
    private String e_birth;

    public String getE_birth() {
        return e_birth;
    }

    public void setE_birth(String e_birth) {
        this.e_birth = e_birth;
    }
    private Integer e_status;
    private Integer d_id;
}
