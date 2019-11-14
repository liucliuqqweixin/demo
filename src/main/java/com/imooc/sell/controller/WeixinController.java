package com.imooc.sell.controller;

import com.imooc.sell.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//手写 获取openid
@RestController
@RequestMapping("weixin")
@Slf4j
public class WeixinController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入方法");
        log.info("code = {}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx480cb6252eb12fb3&secret=9b848bb6a4fab51e36136db9110b0823&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = restTemplate.getForObject(url, String.class);
        System.out.println(JsonUtil.toJson(accessToken));
    }
}
