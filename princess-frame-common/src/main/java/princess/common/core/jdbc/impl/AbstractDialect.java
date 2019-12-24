package princess.common.core.jdbc.impl;

import princess.common.core.jdbc.Dialect;
import princess.common.core.jdbc.SqlUtil;

public abstract class AbstractDialect implements Dialect {
    @Override
    public String getCountSql(String sql) {
        return "SELECT COUNT(*) AS COUNT___Y FROM (" + SqlUtil.removeOrderByExpression(sql) + ") T___Y ";
    }
}
