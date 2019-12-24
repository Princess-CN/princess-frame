package huacloud.jwdsj.module.org.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangHuanGuo
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("DIM_ORG")
public class ProDimOrgEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "编码")
    @TableId("DWBM")
    private String dwbm;
    
    @ApiModelProperty(value = "检察院名字")
    @TableField("DWMC")
    private String dwmc;
    
    @ApiModelProperty(value = "父编码")
    @TableField("PDWBM")
    private Integer pdwbm;

    @ApiModelProperty(value = "父编码")
    @TableField("SZDBM")
    private String szdbm;
    
    @ApiModelProperty(value = "城市名称")
    @TableField("SZDMC")
    private String szdmc;

    @TableField("PSZDBM")
    private Integer pszdbm;

    @TableField("DTBM")
    private String dtbm;


}
