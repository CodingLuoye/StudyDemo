package com.study.zk.lock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分布式锁
 * @author YCKJ1409
 */
public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(300);
        poolConfig.setMaxTotal(200);
        poolConfig.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(poolConfig, "10.128.134.235", 6379, 10000,"123456", 6,null, false);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            Jedis jedis = jedisPool.getResource();
            new MyThread(jedis,countDownLatch,"11111",atomicInteger).start();
            countDownLatch.countDown();
        }
        System.out.println("start---------" + atomicInteger.get());

    }
    static class MyThread extends Thread{

        private Jedis jedis;

        private CountDownLatch countDownLatch;

        private String key;

        private AtomicInteger atomicInteger;

        public MyThread(Jedis jedis, CountDownLatch countDownLatch, String key,AtomicInteger atomicInteger) {
            this.jedis = jedis;
            this.countDownLatch = countDownLatch;
            this.key = key;
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println(currentThread().getName()+ "开始竞争锁---------");
                while(true){
                    boolean flag = RedisTool.tryGetDistributedLock(jedis,key,"1",2000);
                    if(flag){
                        System.out.println(currentThread().getName()+ "获取到了锁" + System.currentTimeMillis());
                        for(int i =0;i<5;i++){
                            atomicInteger.getAndIncrement();
                        }
                        System.out.println(currentThread().getName() + "======"+atomicInteger.get());
                        for (;;){
                            if(RedisTool.releaseDistributedLock(jedis,key,"1")){
                                System.out.println(currentThread().getName()+ "释放了锁" + System.currentTimeMillis());
                                break;
                            }
                        }
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}