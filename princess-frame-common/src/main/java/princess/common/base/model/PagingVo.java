package princess.common.base.model;

import base.page.Pagination;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页查询条件参数
 */
@Data
@SuppressWarnings("serial")
public abstract class PagingVo implements Pagination {

    @ApiModelProperty("开始索引(从0开始)")
    private long offset = 0;

    @ApiModelProperty("查询条数")
    private long limit = 20;
}
