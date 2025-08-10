package org.oaoa.demo.api.info;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.oaoa.demo.common.page.PageVo;
import org.oaoa.demo.common.vo.R;
import org.oaoa.demo.dto.DepDto;
import org.oaoa.demo.dto.DepQueryDto;
import org.oaoa.demo.model.Dep;
import org.oaoa.demo.service.info.DepService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor// 自动注入,针对finall 变量
@RestController
@RequestMapping("/info/dep")
public class DepController {
    private final DepService depService;

    @GetMapping("")
    public R<PageVo<Dep>> depList(DepQueryDto depQueryDto){
        PageVo<Dep> page = depService.getDepPage(depQueryDto);// 调用业务层
        return R.OK(page);// 返回成功
    }// 获取部门列表
    @PostMapping
    public R<?> execAdd(@RequestBody DepDto depDto) {
        depService.addDep(depDto);
        return R.OK();
    }

    @PutMapping
    public R<?> execUpd(@RequestBody DepDto depDto) {
        depService.updateDep(depDto);
        return R.OK();
    }
    @DeleteMapping( "/{d_id}")
    public R<?> execDel(@PathVariable Integer d_id) {
        depService.removeDep(d_id);
        return R.OK();
    }

    @DeleteMapping( "")
    public R<?> execDel(@RequestBody Integer[] dids) {
        depService.removeDep(dids);
        return R.OK();
    }


    @PutMapping("/{id}/{status}")
    public R<?> changeStatus(@PathVariable Integer id,@PathVariable Integer status){
        depService.changeStatus(id,status);
        return R.OK();
    }
}
