package com.haven.adminservice.util;

public class TimeUtilMyself {

    public static Integer returnSenconds(String x){
//        String x="08:30";
        int i = x.indexOf(":");
        Integer hour = Integer.valueOf(x.substring(0, i));
        Integer minute=Integer.valueOf(x.substring(i+1));
        Integer time=hour*60+minute;
//        System.out.println(time);
        return time;
    }


}
