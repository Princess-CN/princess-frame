package princess.common.base.model;

import java.io.Serializable;
import java.util.Date;

import base.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 日期区间
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class DateBounds implements Serializable {
    /** 开始日期 */
    private Date begin = DateUtil.MIN_DATE;
    /** 结束日期 */
    private Date end = DateUtil.MAX_DATE;
}
