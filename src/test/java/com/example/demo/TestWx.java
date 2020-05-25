package com.example.demo;

import com.example.demo.pojo.*;
import com.example.demo.service.UserService;
import com.example.demo.util.TemplateMessageManager;
import com.example.demo.util.WxUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWx {
    @Resource
    private UserService userService;

    @Test
    public void test() {
        List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    public void test1() {
        String openid = "oOZqWv_EpCRdALjK_pVdIz4dPMs4";
        System.out.println(WxUtil.getUserInfo(openid));
    }
}
