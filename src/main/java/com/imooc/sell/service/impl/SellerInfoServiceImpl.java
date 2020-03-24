package com.imooc.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.entity.SellerInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.mapper.SellerInfoMapper;
import com.imooc.sell.service.ISellerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 卖家信息表 服务实现类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Service
public class SellerInfoServiceImpl extends ServiceImpl<SellerInfoMapper, SellerInfo> implements ISellerInfoService {

    @Override
    public SellerInfo findSellerByOpenid(String openid) {
        LambdaQueryWrapper<SellerInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SellerInfo::getOpenid, openid);
        SellerInfo sellerInfo = this.getOne(wrapper);
        return sellerInfo;
    }
}
