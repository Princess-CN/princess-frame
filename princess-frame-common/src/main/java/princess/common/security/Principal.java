package princess.common.security;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录用户
 */
@Data
@SuppressWarnings("serial")
public class Principal implements Serializable {
    // ========================================Fields=========================================
    /** 人员ID */
    private String ryId;
    /** 人员名称 */
    private String ryMc;
    /** 单位ID */
    private String dwId;
    /** 部门ID */
    private String bmId;
    /** 角色ID */
    private String[] jsIds = new String[0];
    /** 功能ID列表 */
    private String[] gnIds = new String[0];

    // ========================================Constants======================================
    /** 未登录用户 */
    public static final Principal NONE;
    static {
        NONE = new Principal();
    }
}
