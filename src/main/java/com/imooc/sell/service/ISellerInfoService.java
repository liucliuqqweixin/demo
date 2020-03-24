package com.imooc.sell.service;

import com.imooc.sell.entity.SellerInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 卖家信息表 服务类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
public interface ISellerInfoService extends IService<SellerInfo> {
    SellerInfo findSellerByOpenid(String openid);
}
