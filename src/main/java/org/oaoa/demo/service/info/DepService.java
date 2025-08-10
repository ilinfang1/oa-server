package org.oaoa.demo.service.info;

import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.dto.DepDto;
import org.oaoa.demo.dto.DepQueryDto;
import org.oaoa.demo.model.Dep;

public interface DepService {
    PageVo<Dep> getDepPage(DepQueryDto depQueryDto);

    void addDep(DepDto depDto);

    void updateDep(DepDto depDto);


    void removeDep(Integer... dIds);

    void changeStatus(Integer id, Integer status);
}
