package com.study.zk.lock;

public interface Lock {
    /**
     * 获得锁
     */
    public void getLock();

    /**
     * 释放锁
     */
    public void unLock();
}
