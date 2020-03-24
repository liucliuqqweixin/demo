package com.imooc.sell.entity;

import java.math.BigDecimal;

import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.sell.enums.StateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(value = "OrderDetail对象", description = "订单详情表")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detail_id", type = IdType.UUID)
    private String detailId;

    @ApiModelProperty(hidden = true)
    private String orderId;
    @ApiModelProperty(hidden = true)
    private String productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "当前价格,单位分")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "数量")
    private Integer productQuantity;

    @ApiModelProperty(value = "小图")
    private String productIcon;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    @TableLogic
    @JsonIgnore
    private Integer del;

}
