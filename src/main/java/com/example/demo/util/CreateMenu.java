// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateMenu.java

package com.example.demo.util;

import com.example.demo.pojo.*;
import net.sf.json.JSONObject;

public class CreateMenu {
    public static void main(String args[]) {
        // 菜单对象
        Button btn = new Button();
        //第一个一级菜单
        btn.getButton().add(new ClickButton("一级点击菜单", "1"));
        //第二个一级菜单
        btn.getButton().add(new ViewButton("一级视频菜单", "http://localhost:8080/weixin/"));
        //第三个一级菜单
        SubButton sb = new SubButton("有子菜单");
        //第三个一级菜单的子菜单
        sb.getSub_button().add(new PhotoOrAlbumButton("二级传图菜单", "31"));
        sb.getSub_button().add(new ClickButton("二级点击菜单", "32"));
        sb.getSub_button().add(new ViewButton("二级注册菜单", "http://127.0.0.1:8080/ssm/GetUserInfo"));
        //加入第三个一级菜单
        btn.getButton().add(sb);
        // object转为json
        JSONObject jsonObject = JSONObject.fromObject(btn);
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxUtil.getAccessToken());
        // 发送请求
        String result = Util.post(url, jsonObject.toString());
        System.out.println(result);
    }
}
