package princess.common.plugin.mybatisplus;

import java.util.function.Function;

import com.baomidou.mybatisplus.core.metadata.IPage;

import base.page.Page;
import base.page.PageUtil;
import base.page.Pagination;
import base.page.SimplePage;

public class PlusHelper {

    public static <T> Page<T> selectPage(Pagination pagination, PagingSelect<T> select) {
        IPage<T> iPage = toIPage(pagination);
        IPage<T> page = select.apply(iPage);
        long current = page.getCurrent();
        long limit = page.getSize();
        long offset = PageUtil.getOffset(current, page.getSize());
        return new SimplePage<>(offset, limit, page.getRecords(), page.getTotal());
    }

    private static <T> IPage<T> toIPage(Pagination pagination) {
        long offset = pagination.getOffset();
        long limit = pagination.getLimit();
        long current = PageUtil.getCurrent(offset, limit);
        return toIPage(current, limit);
    }

    public static <T> IPage<T> toIPage(long current, long limit) {
        return new com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>(current, limit);
    }

    @FunctionalInterface
    public static interface PagingSelect<T> extends Function<IPage<T>, IPage<T>> {}
}
