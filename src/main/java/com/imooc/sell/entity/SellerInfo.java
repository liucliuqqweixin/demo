package com.imooc.sell.entity;

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

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 卖家信息表
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SellerInfo对象", description="卖家信息表")
public class SellerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "seller_id", type = IdType.UUID)
    private String sellerId;
    @NotEmpty(message = "是的撒多所")
    private String username;

    private String password;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    @TableLogic
    private StateEnum del;


}
