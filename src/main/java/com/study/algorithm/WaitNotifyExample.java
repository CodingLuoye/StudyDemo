package com.study.algorithm;

import com.study.service.busi.SaveOrder;

public class WaitNotifyExample {
    private static final Object lock = new Object();


    public static void main(String[] args) {
        Thread producer = new Thread(()->{
            synchronized (lock){
                try{
                    System.out.println("producer : producer ...");
                    Thread.sleep(2000);
                    System.out.println("producer : producer finished:. Notifying consumer.");
                    lock.notify();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(()->{
            synchronized (lock){
                try{
                    System.out.println("consumer : consumer ...");
                    lock.wait();
                    System.out.println("consumer : consumer finished: consumer ....");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        consumer.start();
        producer.start();
    }
}
