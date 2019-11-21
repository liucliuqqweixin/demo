package com.imooc.sell.controller;

import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

//sdk
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = "http://liuc.natapp1.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        System.out.println(code);
        System.out.println(returnUrl);
        WxMpOAuth2AccessToken accessToken = new WxMpOAuth2AccessToken();
        try {
            accessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ServiceException(ResultEnum.WECHAT_MP_ERROR);
        }
        String openId = accessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=oTgZpwUYcj3qUXDoCM0ObMLHtduY";
    }
}
