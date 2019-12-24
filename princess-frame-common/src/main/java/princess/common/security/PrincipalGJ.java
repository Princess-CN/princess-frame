package princess.common.security;

import java.io.Serializable;

import lombok.Data;

/**
 * 高检用户
 */
@Data
public class PrincipalGJ implements Serializable {

    private String dwbm;

    private String dwmc;

    private String yhxx;

    private PrincipalYHXX principalYHXX;

}
