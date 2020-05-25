// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextMessage.java

package com.example.demo.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class TextMessage extends BaseMessage {

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map requestMap, String content) {
        super(requestMap);
        // 设置文本消息的msgtype为text
        setMsgType("text");
        this.content = content;
    }

    @XStreamAlias("Content")
    private String content;
}
