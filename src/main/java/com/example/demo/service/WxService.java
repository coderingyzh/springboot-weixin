package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.util.WxUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Service
public class WxService {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (WxUtil.check(timestamp, nonce, signature)) {
            PrintWriter out = response.getWriter();
            out.print(echostr);
            out.flush();
            out.close();
        } else {
            System.out.println("接入失败");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        // 处理消息和事件推送
        Map requestMap = WxUtil.parseRequest(request.getInputStream());

        //String respXml = "<xml>" +
        //        "  <ToUserName><![CDATA["+ requestMap.get("FromUserName")+"]]></ToUserName>" +
        //        "  <FromUserName><![CDATA["+ requestMap.get("ToUserName")+"]]></FromUserName>" +
        //        "  <CreateTime>"+ System.currentTimeMillis()/1000 +"</CreateTime>" +
        //        "  <MsgType><![CDATA[text]]></MsgType>" +
        //        "  <Content><![CDATA[this is a test]]></Content>" +
        //        "  <MsgId>1234567890123456</MsgId>" +
        //        "</xml>";

        // 准备回复的数据包
        String respXml = WxUtil.getResponse(requestMap);
        System.out.println(respXml);
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.flush();
        out.close();
    }
}
