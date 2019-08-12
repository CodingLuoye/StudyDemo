package com.study.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 測試ScheduledExecutorService
 * scheduleAtFixedRate  以固定的时间执行任务，不考虑任务的执行时间
 *      * {@code initialDelay} then {@code initialDelay+period}, then
 *      * {@code initialDelay + 2 * period}, and so on.
 * scheduleWithFixedDelay 以固定的时间执行任务，但是第二次执行时要加上执行任务的耗时
 *      * {@code initialDelay} then {@code initialDelay+period+taskExcutroTime}
 *      * {@code initialDelay + 2 * period + 2*taskExcutroTime}, and so on.
 * @author YCKJ1409
 */
public class ScheduleTest {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder().setNameFormat("example-schedule-pool-%d").setDaemon(false).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start-------"+System.currentTimeMillis());
                    Thread.sleep(1000);
                    System.out.println("end-------"+System.currentTimeMillis());
                    throw new RuntimeException("报错了");
                } catch (Throwable  e) {
                    System.out.println("error");
                }
            }
        },1,1, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start ********"+System.currentTimeMillis());
                    Thread.sleep(1000);
                    System.out.println("end ********"+System.currentTimeMillis());
                    throw new RuntimeException("报错了");
                } catch (Throwable e) {
                    System.out.println("error");
                }
            }
        },1,1, TimeUnit.SECONDS);
    }
}
