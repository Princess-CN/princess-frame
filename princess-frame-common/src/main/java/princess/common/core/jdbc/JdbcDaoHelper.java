package princess.common.core.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import base.lang.CollectionUtil;
import base.page.Page;
import base.page.Pagination;
import base.page.SimplePage;

/**
 * 基于JDBC的数据访问层支持工具类.<br>
 * @author _YYL
 */
public class JdbcDaoHelper {

    /**
     * 查询单个记录
     * @param <T> 查询的结果泛型
     * @param sql 查询语句
     * @param args 查询参数
     * @param rowMapper 行映射
     * @param jdbcTemplate JDBC模板
     * @return 分页查询結果
     */
    public static <T> T queryOne(String sql, Object[] args, RowMapper<T> rowMapper, JdbcTemplate jdbcTemplate) {
        return CollectionUtil.getFirst(jdbcTemplate.query(sql, args, rowMapper));
    }

    /**
     * 分页查询
     * @param <T> 查询的结果泛型
     * @param sql 查询语句
     * @param args 查询参数
     * @param offset 第一条记录索引
     * @param limit 每页显示记录数
     * @param rowMapper 行映射
     * @param jdbcTemplate JDBC模板
     * @param dialect 数据方言
     * @return 分页查询結果
     */
    public static <T> Page<T> pagedQuery(String sql, Object[] args, long offset, long limit, RowMapper<T> rowMapper,
            JdbcTemplate jdbcTemplate, Dialect dialect) {
        int count = queryCount(sql, args, jdbcTemplate, dialect);
        if (count == 0) {
            return new SimplePage<T>(offset, limit, new ArrayList<T>(), 0);
        }
        List<T> records = queryLimit(sql, args, offset, limit, rowMapper, jdbcTemplate, dialect);
        return new SimplePage<T>(offset, limit, records, count);
    }

    /**
     * 分页查询
     * @param <T> 查询的结果泛型
     * @param sql 查询语句
     * @param args 查询参数
     * @param pagination 分页条件
     * @param rowMapper 行映射
     * @param jdbcTemplate JDBC模板
     * @param dialect 数据方言
     * @return 分页查询結果
     */
    public static <T> Page<T> pagedQuery(CharSequence sql, List<Object> args, Pagination pagination,
            RowMapper<T> rowMapper, JdbcTemplate jdbcTemplate, Dialect dialect) {
        return pagedQuery(sql.toString(), args.toArray(), pagination.getOffset(), pagination.getLimit(), rowMapper,
                jdbcTemplate, dialect);
    }

    /**
     * 查询SQL能查询到的记录总数
     * @param sql 查询语句
     * @param args 查询参数
     * @param rowMapper 行映射
     * @param jdbcTemplate JDBC模板
     * @param dialect 数据方言
     * @return 能查询到的记录总数
     */
    private static int queryCount(String sql, Object[] args, JdbcTemplate jdbcTemplate, Dialect dialect) {
        return jdbcTemplate.queryForObject(dialect.getCountSql(sql), args, Long.class).intValue();
    }

    /**
     * 查询SQL能查询到的记录总数
     * @param <T> 查询的结果泛型
     * @param sql 查询语句
     * @param args 查询参数
     * @param offset 第一条记录索引
     * @param limit 每页显示记录数
     * @param rowMapper 行映射
     * @param jdbcTemplate JDBC模板
     * @param dialect 数据方言
     * @return 能查询到的记录总数
     */
    private static <T> List<T> queryLimit(String sql, Object[] args, long offset, long limit, RowMapper<T> rowMapper,
            JdbcTemplate jdbcTemplate, Dialect dialect) {
        return jdbcTemplate.query(dialect.getLimitSql(sql, offset, limit), args, rowMapper);
    }
}
