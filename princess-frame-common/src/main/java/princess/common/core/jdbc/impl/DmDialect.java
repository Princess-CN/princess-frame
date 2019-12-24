package princess.common.core.jdbc.impl;

import princess.common.core.jdbc.Dialect;

/**
 * JDBC查询方言DM实现，主要用于提供分页查询<br>
 */
public class DmDialect extends AbstractDialect implements Dialect {

    public static final DmDialect INSTANCE = new DmDialect();

    @Override
    public String getLimitSql(String sql, long start, long limit) {
        return sql + " limit " + start + "," + limit;
    }

    @Override
    public String testQuery() {
        return "select 1 from dual";
    }
}
