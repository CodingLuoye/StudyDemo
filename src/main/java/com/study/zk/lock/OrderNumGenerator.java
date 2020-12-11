package com.study.zk.lock;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YCKJ1409
 *
 */
public class OrderNumGenerator {

    public static int count = 0;

    public static Object lock = new Object();

    public String getNumber(){
        synchronized(lock){
            SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            return simpt.format(new Date())+"-"+ ++count + "_"+Thread.currentThread().getId();
        }
    }
}
