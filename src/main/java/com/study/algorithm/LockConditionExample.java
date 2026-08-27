package com.study.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionExample {
    private static final Lock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(()->{
            lock.lock();
            try{
                System.out.println("producer : producer ...");
                Thread.sleep(2000);
                System.out.println("producer : producer finished:. Notifying consumer.");
                condition.signal();
            }catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread consumer = new Thread(()->{
            lock.lock();
            try{
                System.out.println("consumer : consumer ...");
                condition.await();
                System.out.println("consumer : consumer finished:. consumer.....");
            }catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        consumer.start();
        Thread.sleep(10);
        producer.start();
    }

}
