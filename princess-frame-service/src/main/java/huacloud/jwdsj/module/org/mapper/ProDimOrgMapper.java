package huacloud.jwdsj.module.org.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import huacloud.jwdsj.module.org.model.entity.ProDimOrgEntity;
import huacloud.jwdsj.module.org.model.qo.OrgIndexQo;
import huacloud.jwdsj.module.org.model.vo.ProDimOrgEntityVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangHuanGuo
 * @since 2019-09-25
 */
@Mapper
public interface ProDimOrgMapper extends BaseMapper<ProDimOrgEntity> {
	List<ProDimOrgEntityVo> selectProList(@Param("orgIndexQo")OrgIndexQo orgIndexQo);

}
