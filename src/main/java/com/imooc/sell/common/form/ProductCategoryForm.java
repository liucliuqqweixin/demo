package com.imooc.sell.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductCategoryForm {
    @ApiModelProperty(value = "类目编号")
    private Integer type;
    @ApiModelProperty(value = "类目名字")
    private String name;
}
