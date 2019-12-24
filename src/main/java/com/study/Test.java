package com.study;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //今天零点零分零秒的毫秒数
//        long start = System.currentTimeMillis() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
//        //今天23点59分59秒的毫秒数
//        long end = start + 24 * 60 * 60 * 1000 - 1;
//        System.out.println(start);
//        System.out.println(end);
//        System.out.println(simpleDateFormat.format(new Date(start)));
//        System.out.println(simpleDateFormat.format(new Date(end)));
//
//        String startTime = "2019-07-11 07:59:02";
//        try {
//            long value = simpleDateFormat.parse(startTime).getTime();
//            long start2 =
//                    value / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
//            System.out.println(simpleDateFormat.format(new Date(start2)));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            long start3 = sdf.parse(sdf.format(new Date())).getTime();
            //今天23点59分59秒的毫秒数
            long end3 = start3 + 24 * 60 * 60 * 1000 - 1;
            System.out.println(simpleDateFormat.format(new Date(start3)));
            System.out.println(simpleDateFormat.format(new Date(end3)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer totalCount = 9;
        int i =0;
        if(totalCount%10==0){
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
            i = totalCount / 10;
        }else{
            //不整除，就要在加一页，来显示多余的数据。
            i= totalCount / 10 +1;
        }
        for(int j=1;j<=i;j++){
            System.out.println(j);
        }
    }
}
