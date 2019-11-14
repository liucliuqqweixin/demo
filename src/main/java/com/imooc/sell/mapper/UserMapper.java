package com.imooc.sell.mapper;

import com.imooc.sell.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuc
 * @since 2019-11-14
 */
public interface UserMapper extends BaseMapper<User> {
    Integer insertBach(@Param("users") List<User> users);

    List<User> seletListUser();

    Integer insertUser(@Param("user") User user);
}
