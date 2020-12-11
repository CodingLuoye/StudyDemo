package com.study.model;

import org.openjdk.jol.info.ClassLayout;

import java.util.Calendar;
import java.util.Date;

public class TestObject {
    public static void main(String[] args) {
//        Object object = new Object();
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//
//        synchronized (object){
//            System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        }
        System.out.println(getCurrentTime());
    }

    /**
     * 获得当天零时零分零秒
     * @return
     */
    public static long getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }
}
