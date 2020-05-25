package com.example.demo.controller;

import com.example.demo.service.WxService;
import com.example.demo.util.Util;
import com.example.demo.util.WxUtil;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/WxController")
public class WxController {
    @Resource
    private WxService wxService;

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        wxService.doGet(request, response);
        System.out.println("nihao");
    }

    /**
     * 接受消息和事件推送
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        wxService.doPost(request, response);
    }

    /**
     * 获取用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/GetUserInfo", method = RequestMethod.GET)
    public void GetUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取code
        String code = request.getParameter("code");
        // code换取access_token,openid等信息
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APPID", "wxcf5f83409e734bc3").replace("SECRET", "2f89913e0dfb81c38a1007c3473ba9c0").replace("CODE", code);
        String result = Util.get(url);
        System.out.println(result);
        // 刷新access_token
        String refresh_token = JSONObject.fromObject(result).getString("refresh_token");
        url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        url = url.replace("APPID", "wxcf5f83409e734bc3").replace("REFRESH_TOKEN", refresh_token);
        result = Util.get(url);
        System.out.println(result);
        // 拉取用户信息
        String at = JSONObject.fromObject(result).getString("access_token");
        String openid = JSONObject.fromObject(result).getString("openid");
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", at).replace("OPENID", openid);
        result = Util.get(url);
        System.out.println(result);
    }
}
