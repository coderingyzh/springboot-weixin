// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserService.java

package com.example.demo.service;


import com.example.demo.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
