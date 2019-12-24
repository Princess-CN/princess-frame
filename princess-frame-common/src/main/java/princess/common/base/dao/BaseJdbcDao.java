package princess.common.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import princess.common.core.jdbc.Dialect;
import princess.common.core.jdbc.impl.DmDialect;


public abstract class BaseJdbcDao {

    protected static final Dialect DIALECT = DmDialect.INSTANCE;

    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
