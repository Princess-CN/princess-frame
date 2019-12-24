package huacloud.jwdsj.module.org.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import huacloud.jwdsj.module.org.model.qo.OrgIndexQo;
import huacloud.jwdsj.module.org.model.vo.ProDimOrgEntityVo;
import huacloud.jwdsj.module.org.service.ProDimOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(value = "地图城市展示", tags = "地图城市展示")
@RestController
@RequestMapping("/org")
@Slf4j
public class OrgProDimOrgController {
	@Autowired
	ProDimOrgService proDimOrgService;
	
	@ApiOperation("查询城市地区")
	@ApiImplicitParams({ @ApiImplicitParam(name = "dwbm", value = "地区编码", required = true, dataType = "String"),
	    	@ApiImplicitParam(name = "beginDate", value = "开始时间", required = true, dataType = "String"),
	    	@ApiImplicitParam(name = "endDate", value = "结束时间", required = true, dataType = "String"), })
    @PostMapping(value = "/listProList")
    public List<ProDimOrgEntityVo> listProList(@RequestBody OrgIndexQo orgIndexQo) {
		List<ProDimOrgEntityVo> syXzIndexVo = proDimOrgService.listProList(orgIndexQo);
        return syXzIndexVo;
    }	

}
