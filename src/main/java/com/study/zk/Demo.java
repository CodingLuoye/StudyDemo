package com.study.zk;

import java.util.List;

/**
 * 测试zookeeper
 * @author YCKJ1409
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        BaseZookeeper zookeeper = new BaseZookeeper();
        zookeeper.connectZookeeper("127.0.0.1:2181");
        List<String> children = zookeeper.getChildren("/");
        for (String nodeName: children) {
            String data = zookeeper.getData("/"+nodeName);
            System.out.println(data);
        }
        System.out.println(children);
        zookeeper.closeConnection();
    }

}