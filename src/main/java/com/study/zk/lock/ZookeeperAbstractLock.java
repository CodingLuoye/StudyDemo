package com.study.zk.lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author YCKJ1409
 */
public class ZookeeperAbstractLock extends AbstracLock {

    //ZK连接地址
    private static final String CONNECTSTRING = "192.168.73.128:2181";

    protected ZkClient zkClient = new ZkClient(CONNECTSTRING);

    protected static final String PATH = "/lock";

    protected static final String PATH2 = "/lock2";

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public void waitLock() {

    }
}
