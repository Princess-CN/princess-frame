package huacloud.jwdsj.module.org.model.vo;

import java.io.Serializable;

import huacloud.jwdsj.module.org.model.entity.ProDimOrgEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel("全国城市列表")
public class ProDimOrgEntityVo extends ProDimOrgEntity implements Serializable{
	@ApiModelProperty("受理数")
	private Long sls;
	@ApiModelProperty("办结数")
	private Long bjs;
	@ApiModelProperty("在办数")
	private Long zbs;

}
