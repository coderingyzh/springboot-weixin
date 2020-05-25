// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubButton.java

package com.example.demo.pojo;

import java.util.ArrayList;
import java.util.List;

public class SubButton extends AbstractButton {

    public List getSub_button() {
        return sub_button;
    }

    public void setSub_button(List sub_button) {
        this.sub_button = sub_button;
    }

    public SubButton(String name) {
        super(name);
        sub_button = new ArrayList();
    }

    private List sub_button;
}
