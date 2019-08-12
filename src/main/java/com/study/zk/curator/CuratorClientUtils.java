package com.study.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Curator Zookeeper 实现
 * @author YCKJ1409
 */
public class CuratorClientUtils {

    private static CuratorFramework curatorFramework;
    private final static String CONNECTSTRING="192.168.73.128:2181";


    public static CuratorFramework getInstance(){
        /*重试连接3次,后面每次叠加*/
        curatorFramework= CuratorFrameworkFactory.
                newClient(CONNECTSTRING,5000,5000,
                        new ExponentialBackoffRetry(1000,3));
        /*start方法启动连接*/
        curatorFramework.start();
        return curatorFramework;
    }
}
