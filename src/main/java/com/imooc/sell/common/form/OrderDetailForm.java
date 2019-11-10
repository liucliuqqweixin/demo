package com.imooc.sell.common.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imooc.sell.enums.StateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderDetailFrom对象", description="查询对象")
public class OrderDetailForm implements Serializable {

    private static final long serialVersionUID = 1L;



    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "当前价格,单位分")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "数量")
    private Integer productQuantity;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "状态 0:在售 1：不在售")
    private Integer state;


}
