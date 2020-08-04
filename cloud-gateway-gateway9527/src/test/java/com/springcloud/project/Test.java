package com.springcloud.project;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

@RunWith(SpringRunner.class)  // 用SpringRunner来运行
@SpringBootTest
public class Test {

    @org.junit.Test
    public void test(){
        ZonedDateTime time = ZonedDateTime.now();   // 当地当前时间
        System.out.println(time);  // 2020-07-27T21:39:02.978137+08:00[Asia/Shanghai]
    }

}


