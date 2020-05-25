package com.example.demo.util;

/**
 * 模板消息接口
 */
public class TemplateMessageManager {

    String at = WxUtil.getAccessToken();
    /**
     * 设置行业
     */
    public String set() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + at;
        String data = "{\n" +
                "\"industry_id1\":\"2\",\n" +
                "\"industry_id2\":\"3\"\n" +
                "}";
        String result = Util.post(url, data);
        return result;
    }
    /**
     * 获取行业
     */
    public String get() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + at;
        String result = Util.get(url);
        return result;
    }

    /**
     * 发送模板消息
     * @return
     */
    public String sendTemplateMessage() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + at;
        String data = "";
        String result = Util.post(url, data);
        return result;
    }
}
