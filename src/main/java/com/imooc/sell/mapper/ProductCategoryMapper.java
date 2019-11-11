package com.imooc.sell.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.imooc.sell.common.VO.ProductCategoryVO;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.common.form.ProductCategoryForm;
import com.imooc.sell.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    List<ProductCategoryVO> selectList1(@Param("form") ProductCategoryForm form);


}
