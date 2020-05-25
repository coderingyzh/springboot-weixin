// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Util.java

package com.example.demo.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Util {

    /**
     * 向指定地址发送一个post请求并带着data数据
     * @param url
     * @param data
     * @return
     */
    public static String post(String url, String data) {
        PrintWriter out = null;
        try {
            URL urlObj = new URL(url);
            URLConnection conn = urlObj.openConnection();
            // 要发送数据出去，必须要设置为可发送数据状态
            conn.setDoOutput(true);
            // 获取输出流
            //OutputStream os = conn.getOutputStream();
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 写出数据
            //os.write(data.getBytes());
            //os.close();
            // 发送请求参数
            out.print(data);
            // flush输出流的缓冲
            out.flush();
            // 获取输入流
            InputStream is = conn.getInputStream();
            byte b[] = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len, "UTF-8"));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向指定的地址发送get请求
     * @param url
     * @return
     */
    public static String get(String url) {
        try {
            URL urlObj = new URL(url);
            // 开连接
            URLConnection conn = urlObj.openConnection();
            InputStream is = conn.getInputStream();
            byte b[] = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len, "UTF-8"));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
