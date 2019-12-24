package huacloud.jwdsj.module.org.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import huacloud.jwdsj.module.org.mapper.ProDimOrgMapper;
import huacloud.jwdsj.module.org.model.qo.OrgIndexQo;
import huacloud.jwdsj.module.org.model.vo.ProDimOrgEntityVo;
import huacloud.jwdsj.module.org.service.ProDimOrgService;
@Service
public class ProDimOrgServiceImpl implements ProDimOrgService{
	@Autowired
	ProDimOrgMapper proDimOrgMapper;

	@Override
	public List<ProDimOrgEntityVo> listProList(OrgIndexQo orgIndexQo) {
		List<ProDimOrgEntityVo> proList=proDimOrgMapper.selectProList(orgIndexQo);
		return proList;
	}

}
