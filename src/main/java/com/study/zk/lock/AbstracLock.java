package com.study.zk.lock;

public abstract class AbstracLock implements Lock {
    @Override
    public void getLock() {
        if(tryLock()){
            System.out.println("获取锁");
        }else {
            waitLock();
            getLock();
        }
    }

    @Override
    public void unLock() {

    }

    public abstract  boolean tryLock();

    public abstract void waitLock();
}
