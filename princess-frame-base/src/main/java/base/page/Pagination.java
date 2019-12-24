package base.page;

import java.io.Serializable;

/**
 * 分页查询条件参数<br>
 */
public interface Pagination extends Serializable {

    /** 开始数据索引号 */
    long DEFAULT_OFFSET = 0;
    /** 默认查询记录数 */
    long DEFAULT_LIMIT = 20;

    /**
     * 获取从第几条数据开始查询
     * @return 查询的偏移量
     */
    public long getOffset();

    /**
     * 获取每页显示记录数
     * @return 每页显示记录数
     */
    public long getLimit();
}
