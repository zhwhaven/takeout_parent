package com.haven.orderservice;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    @Test
    public void test(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String nowtime=sdf.format(d);
        System.out.println("当前时间：" +nowtime );


    }
}
