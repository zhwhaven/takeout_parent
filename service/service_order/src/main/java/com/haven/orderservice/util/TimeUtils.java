package com.haven.orderservice.util;

import lombok.Data;

@Data
public class TimeUtils {
    public static String getReceiveTime (String startTime,Integer pushTime){
        int i = startTime.indexOf(":");
        Integer hour=Integer.valueOf(startTime.substring(0,i));
        Integer minute=Integer.valueOf(startTime.substring(i+1));
        Integer allminute=hour*60+minute+pushTime;
        Integer hour1= allminute/60;
        Integer minute1= allminute%60;
        return hour1+":"+minute1;
    }
}
