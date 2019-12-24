package base.page;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分页查询的结果数据 <br>
 */
@Data
@Accessors(chain = true)
@SuppressWarnings("serial")
public class SimplePage<T> implements Page<T> {

    // =================================Fields================================================
    /** 开始查询 的数据索引号 (从0开始) */
    private long offset = 0;
    /** 每页条数 */
    private long limit = Pagination.DEFAULT_LIMIT;
    /** 总记录数 */
    private long total = 0;
    /** 当前页数据 */
    private List<T> records;

    // =================================Constructors===========================================
    /**
     * 构造函数
     */
    public SimplePage() {
        this.records = new ArrayList<T>();
    }

    /**
     * 构造函数
     * @param records 当前页数据
     */
    public SimplePage(List<T> records) {
        this(0, records.size(), records, records.size());
    }

    /**
     * 构造函数
     * @param offset 记录开始索引号
     * @param limit 页面最大记录数
     * @param records 当前页数据
     * @param total 总记录数
     */
    public SimplePage(long offset, long limit, List<T> records, long total) {
        this.offset = offset;
        this.limit = limit;
        this.records = records;
        this.total = total;
    }
}
