// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Button.java

package com.example.demo.pojo;

import java.util.ArrayList;
import java.util.List;

public class Button {

    public Button() {
        button = new ArrayList();
    }

    public List getButton() {
        return button;
    }

    public void setButton(List button) {
        this.button = button;
    }

    private List button;
}
