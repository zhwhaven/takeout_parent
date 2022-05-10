package com.haven.orderservice.util;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class TimeUtils {
    public static String getReceiveTime (String startTime,Integer pushTime){
        int i = startTime.indexOf(":");
        Integer hour=Integer.valueOf(startTime.substring(0,i));
        Integer minute=Integer.valueOf(startTime.substring(i+1));
        Integer allminute=hour*60+minute+pushTime;
        Integer hour1= allminute/60;
        Integer minute1= allminute%60;
        String rehour="";
        String reminute="";
        if(hour1/10==0){
            rehour="0"+hour1;
        }else{
            rehour=String.valueOf(hour1);
        }
        if(minute1/10==0){
            reminute="0"+minute1;
        }else {
            reminute=String.valueOf(minute1);
        }
        return rehour+":"+reminute;
    }

    public static String getNowTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String nowtime=sdf.format(d);
        return nowtime;
    }
}
