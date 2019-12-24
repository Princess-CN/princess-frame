package base.page;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分页查询条件参数<br>
 */
@Data
@Accessors(chain = true)
@SuppressWarnings("serial")
public class SimplePagination implements Pagination {

    // =================================Fields=================================================
    /** 开始查询 的数据索引号 (从0开始) */
    private long offset = 0;

    /** 每页条数 */
    private long limit = DEFAULT_LIMIT;

    // =================================Constructors===========================================
    /**
     * 构造函数
     */
    public SimplePagination() {
        this(0, DEFAULT_LIMIT);
    }

    /**
     * 构造函数
     * @param offset 查询数据开始索引
     * @param limit 查询记录数
     */
    public SimplePagination(long offset, long limit) {
        this.offset = offset;
        this.limit = limit;
    }
}
