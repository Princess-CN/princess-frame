package huacloud.jwdsj.common.component.enums;

public enum IdGroup {
    /** 默认 */
    DEFAULT(""),
    /** 部门 表 */
    DEPT(""),
    /** 机构表 */
    ORG(""),
    /** 权限表 */
    PERMISSION(""),
    /** 角色表 */
    ROLE(""),
    /** 角色权限关联表 */
    ROLE_PERMISSION(""),
    /** 系统人员 */
    XT_RY(""),
    /** 用户权限关联表 */
    USER_PERMISSION(""),
    /** 用户角色关联表 */
    USER_ROLE(""),
    /**
     * 指标表
     */
    RW_ZB("");

    public final String prefix;

    private IdGroup(String prefix) {
        this.prefix = prefix;
    }
}
