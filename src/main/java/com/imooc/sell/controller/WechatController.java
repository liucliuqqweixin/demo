package com.imooc.sell.controller;

import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.config.ProjectUrl;
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
    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrl projectUrl;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = projectUrl.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
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

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        String url = projectUrl.getWechatOpenAuthorize() + "/sell/qr/oTgZpwUYcj3qUXDoCM0ObMLHtduY";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        log.info("【微信网页登陆】获取code：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }
//, @RequestParam("state") String returnUrl
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code) {
        WxMpOAuth2AccessToken accessToken = new WxMpOAuth2AccessToken();
        try {
            accessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ServiceException(ResultEnum.WECHAT_MP_ERROR);
        }
        String openId = accessToken.getOpenId();
        String redirectUrl = "http://liuc.natapp1.cc/sell/seller/login";
        return "redirect:" +redirectUrl+ "?openid="+openId;

    }

    
}
