package com.study.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.log4j.Logger;
import java.util.concurrent.*;

public class ThreadTest {
    private static Logger logger = Logger.getLogger(ThreadTest.class);

    public static void main(String[] args) {
        logger.info("---");
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("test")
                .setUncaughtExceptionHandler((thread,throwable)-> logger.info("ThreadPool {} got exception"+thread.getName(),
                        throwable))
                .build();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory);
        for (int i = 0; i < 20; i++) {
            TestThread testThread = new TestThread(i);
            executorService.execute(testThread);
        }
        executorService.shutdown();
    }

    static class TestThread implements Runnable{

        private int id;

        public TestThread(int id){
            this.id = id;
        }

        @Override
        public void run() {
            if(id % 5 ==0 ){
                int a = 1/0;
            }
            System.out.println("当前线程name"+id);
        }
    }
}
