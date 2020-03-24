package com.imooc.sell.controller;

import com.imooc.sell.common.constant.CookieConstant;
import com.imooc.sell.common.constant.RedisConstant;
import com.imooc.sell.config.ProjectUrl;
import com.imooc.sell.entity.SellerInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.ISellerInfoService;
import com.imooc.sell.service.RedisService;
import com.imooc.sell.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.String;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private ISellerInfoService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrl projectUrlConfig;
    @Autowired
    private RedisService redisService;

    @GetMapping("/login")
    public String login(@RequestParam("openid") String openid,
                        HttpServletResponse response, Model model) {

        //1. openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerByOpenid(openid);
        if (sellerInfo == null) {
            model.addAttribute("msg", ResultEnum.LOGIN_FAIL.getMsg());
            model.addAttribute("url", "/sell/seller/order/list");
            return "common/error";
        }

        String token = UUID.randomUUID().toString();

        redisService.set(String.format(RedisConstant.TOKEN_PREFIX, token), openid);
        redisService.expire(String.format(RedisConstant.TOKEN_PREFIX, token), 50);

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        return "redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            redisService.remove(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        model.addAttribute("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        model.addAttribute("url", "/sell/seller/order/list");
        return "common/success";
    }
}
