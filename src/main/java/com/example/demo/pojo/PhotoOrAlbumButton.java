// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PhotoOrAlbumButton.java

package com.example.demo.pojo;

import java.util.ArrayList;
import java.util.List;


public class PhotoOrAlbumButton extends AbstractButton {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List getSub_button() {
        return sub_button;
    }

    public void setSub_button(List sub_button) {
        this.sub_button = sub_button;
    }

    public PhotoOrAlbumButton(String name, String key) {
        super(name);
        type = "pic_photo_or_album";
        sub_button = new ArrayList();
        this.key = key;
    }

    private String type;
    private String key;
    private List sub_button;
}
