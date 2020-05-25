package com.example.demo.util;

import com.example.demo.pojo.*;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WxUtil {

    private static String TOKEN = "weixin";
    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String APPID = "wxcf5f83409e734bc3";
    private static final String APPSECRET = "2f89913e0dfb81c38a1007c3473ba9c0";
    // 存储token
    private static AccessToken at;

    /**
     * 获取token
     */
    private static void getToken() {
        String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        String tokenStr = Util.get(url);
        // json转化成object
        JSONObject jsonObject = JSONObject.fromObject(tokenStr);
        String token = jsonObject.getString("access_token");
        String expiresIn = jsonObject.getString("expires_in");
        // 创建并存储token
        at = new AccessToken(token, expiresIn);
    }

    /**
     * 向外界暴露的获取token方法
     *
     * @return
     */
    public static String getAccessToken() {
        if (at == null || at.isExpired()) {
            getToken();
        }
        return at.getAccessToken();
    }

    /**
     * 将token、timestamp、nonce三个参数进行字典序排序,获得加密后的字符串可与signature对比
     *
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public static boolean check(String timestamp, String nonce, String signature) {
        String strs[] = {TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        String str = strs[0] + strs[1] + strs[2];
        String mysig = sha1(str);
        return mysig.equalsIgnoreCase(signature);
    }

    /**
     * sha1加密
     *
     * @param src
     * @return
     */
    private static String sha1(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte digest[] = md.digest(src.getBytes());
            char chars[] = {
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'
            };
            StringBuilder sb = new StringBuilder();
            byte abyte0[] = digest;
            int i = abyte0.length;
            for (int j = 0; j < i; j++) {
                byte b = abyte0[j];
                sb.append(chars[b >> 4 & 0xf]);
                sb.append(chars[b & 0xf]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析xml数据包
     *
     * @param is
     * @return
     */
    public static Map parseRequest(InputStream is) {
        Map map = new HashMap();
        SAXReader reader = new SAXReader();
        try {
            // 读取输入流，获取文档对象
            Document document = reader.read(is);
            // 根据文档对象获取节点
            Element root = document.getRootElement();
            // 获取根节点的所有子节点
            List<Element> elements = root.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        return map;
    }

    /**
     * 用于处理所有的事件和消息回复
     *
     * @param requestMap
     * @return 返回的是xml数据包
     */
    public static String getResponse(Map requestMap) {
        BaseMessage msg = null;
        String msgType = (String) requestMap.get("MsgType");
        switch (msgType) {
            case "text":
                msg = dealTextMessage(requestMap);
                break;
            case "image":
                msg = dealImage(requestMap);
                break;
            case "voice":

                break;
            case "video":

                break;
            case "music":

                break;
            case "news":

                break;
            case "shortvideo":

                break;
            case "local":

                break;
            case "link":

                break;
            case "event":
                msg = dealEvent(requestMap);
                break;
        }
        // 把消息对象处理成xml数据包
        if (msg != null) {
            return beanToXml(msg);
        }
        return null;
    }

    /**
     * 进行图片识别
     *
     * @param requestMap
     * @return
     */
    private static BaseMessage dealImage(Map requestMap) {

        return null;
    }

    /**
     * 处理事件推送
     *
     * @param requestMap
     * @return
     */
    private static BaseMessage dealEvent(Map<String, String> requestMap) {
        String event = requestMap.get("Event");
        switch (event) {
            case "CLICK":
                return dealClick(requestMap);
            case "VIEW":
                return dealView(requestMap);
            default:
                break;
        }
        return null;
    }

    /**
     * 处理view菜单按钮
     *
     * @param requestMap
     * @return
     */
    private static BaseMessage dealView(Map<String, String> requestMap) {
        return null;
    }

    /**
     * 处理click菜单按钮
     *
     * @param requestMap
     * @return
     */
    private static BaseMessage dealClick(Map<String, String> requestMap) {
        String key = requestMap.get("EventKey");
        switch (key) {
            //点击一级菜单,对应key的值
            case "1":
                // 处理点击第一个一级菜单
                return new TextMessage(requestMap, "你点了我一下！");
            case "32":
                // 处理点击第三个一级菜单的第二个子菜单
                break;
            default:
                break;

        }
        return null;
    }

    /**
     * 把消息对象处理成xml数据包
     *
     * @param msg
     * @return
     */
    private static String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(VideoMessage.class);
        String xml = stream.toXML(msg);
        return xml;
    }

    /**
     * 处理文本消息
     *
     * @param requestMap
     * @return
     */
    private static BaseMessage dealTextMessage(Map requestMap) {
        // 用户发来的内容
        String msg = (String) requestMap.get("Content");
        if (msg.equals("图文")) {
            List articles = new ArrayList();
            articles.add(new Article("这是图文消息的标题", "这是图文消息的详细介绍", "http://mmbiz.qpic.cn/mmbiz_jpg/gOApa5iafm1qYZRVzr7hFnE1L30kp3IUIZDvUCYSaD2HyAAuib5mrDBSgPOWbxtIySjD60qjUsPo07lNm06RQYzw/0", "htt://www.baidu.com"));
            NewsMessage nm = new NewsMessage(requestMap, articles);
            return nm;
        }
        if (msg.equals("登录")) {
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcf5f83409e734bc3&redirect_uri=\n" +
                    "http://www.yangzhenh.top/WxController/GetUserInfo&response_type=code&scope=snsapi_userinfo#wechat_redirect";
            TextMessage tm = new TextMessage(requestMap, (new StringBuilder()).append("<a href=\"").append(url).append("\">点击登录</a>").toString());
            return tm;
        } else {
            TextMessage tm = new TextMessage(requestMap, msg);
            return tm;
        }
    }

    /**
     * 获取用户信息
     *
     * @param openid
     * @return
     */
    public static String getUserInfo(String openid) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", getAccessToken()).replace("OPENID", openid);
        String result = Util.get(url);
        return result;
    }

    /**
     * 上传临时素材
     *
     * @param path 上传文件的路径
     * @param type 上传文件的类型
     * @return
     */
    public static String upload(String path, String type) {
        File file = new File(path);
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
            url.replace("ACCESS_TOKEN", getAccessToken()).replace("TYPE", type);
            URL urlObj = null;
            urlObj = new URL(url);
            // 强制转换成https
            HttpsURLConnection conn = (HttpsURLConnection) urlObj.openConnection();
            // 设置连接的信息
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头信息
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置数据边界
            String boundary = "-----" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            // 获取输出流
            OutputStream out = conn.getOutputStream();
            // 创建输入流
            InputStream is = new FileInputStream(file);
            // 准备数据
            // 第一部分：头部信息
            StringBuilder sb = new StringBuilder();
            sb.append("---");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            out.write(sb.toString().getBytes());
            out.flush();
            out.close();
            // 第二部分：文件内容
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                out.write(b, 0, len);
            }
            // 第三部分：尾部信息
            String foot = "\r\n--" + boundary + "--\r\n";
            out.write(foot.getBytes());
            out.flush();
            out.close();
            // 读取数据
            InputStream is2 = conn.getInputStream();
            StringBuilder sb2 = new StringBuilder();
            while ((len = is2.read(b)) != -1) {
                sb2.append(new String(b, 0, len));
            }
            return sb2.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取带参数的二维码ticket
     * @return
     */
    public static String getQrCodeTicket() {
        String at = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
        // 生成临时二维码，时间为600秒
        //{"expire_seconds": 600, "action_name": "QR_STR_SCENE", "action_info": {"scene": {"scene_str": "test"}}}
        // 生成永久二维码
        String data = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}";
        String result = Util.post(url, data);
        String ticket = JSONObject.fromObject(result).getString("ticket");
        return ticket;
    }
}
