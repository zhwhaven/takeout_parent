package com.haven.adminservice;

//import com.haven.utilscommon.util.MD5;
import com.haven.adminservice.util.TimeUtilMyself;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    @Test
    public void test(){
//        String encrypt = MD5.encrypt("1477112645");
//        System.out.println(encrypt);


//        String startTime = new String("08:30");
//        Date date = new Date(startTime);
//        System.out.println(date);

        String x="08:30";
        Integer senconds = TimeUtilMyself.returnSenconds(x);
        System.out.println(senconds);
//        int i = x.indexOf(":");
//        Integer hour = Integer.valueOf(x.substring(0, i));
//        Integer minute=Integer.valueOf(x.substring(i+1));
//        Integer time=hour*60+minute;
//        System.out.println(time);

    }
}
