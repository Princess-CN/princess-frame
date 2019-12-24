package huacloud.jwdsj.module.org.service;

import java.util.List;


import org.springframework.stereotype.Service;

import huacloud.jwdsj.module.org.model.qo.OrgIndexQo;
import huacloud.jwdsj.module.org.model.vo.ProDimOrgEntityVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangHuanGuo
 * @since 2019-09-25
 */

public interface ProDimOrgService {
	List<ProDimOrgEntityVo> listProList(OrgIndexQo orgIndexQo);
}
