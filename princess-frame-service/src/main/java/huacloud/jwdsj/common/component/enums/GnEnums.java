package huacloud.jwdsj.common.component.enums;

public enum GnEnums {
    GN_LB_CD(0),
    GN_LB_CZ(1);

    private int value;

    private GnEnums(int value){this.value = value;}

    public int getValue() {
        return value;
    }
}
