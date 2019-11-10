package com.imooc.sell.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.imooc.sell.enums.StateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单主表
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderMaster对象", description="订单主表")
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.UUID)
    private String orderId;

    @ApiModelProperty(value = "买家名字")
    private String buyerName;

    @ApiModelProperty(value = "买家电话")
    private String buyerPhone;

    @ApiModelProperty(value = "买家地址")
    private String buyerAddress;

    @ApiModelProperty(value = "买家微信openid")
    private String buyerOpenid;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "订单状态, 默认为新下单")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付状态, 默认未支付")
    private Integer payStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    @TableLogic
    private StateEnum del;


}
