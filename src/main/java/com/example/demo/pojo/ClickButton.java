// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClickButton.java

package com.example.demo.pojo;


// Referenced classes of package com.exull.pojo:
//            AbstractButton

public class ClickButton extends AbstractButton {

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

    public ClickButton(String name, String key) {
        super(name);
        type = "click";
        this.key = key;
    }

    private String type;
    private String key;
}
