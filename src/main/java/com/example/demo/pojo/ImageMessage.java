// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageMessage.java

package com.example.demo.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("Image")
public class ImageMessage extends BaseMessage {

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public ImageMessage(Map requestMap, String mediaId) {
        super(requestMap);
        setMsgType("image");
        this.mediaId = mediaId;
    }

    @XStreamAlias("MediaId")
    private String mediaId;
}
