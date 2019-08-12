package com.study.zk.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * ZkClient Zookeeper 实现
 * @author YCKJ1409
 */
public class CreateSessionDemo  {
    private final static String CONNECTSTRING="192.168.73.128:2181";
    private static CountDownLatch countDownLatch=new CountDownLatch(1);
    public static void main(String[] args) throws Exception {
        final ZooKeeper zooKeeper=new ZooKeeper(CONNECTSTRING, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //如果当前的连接状态是连接成功的，那么通过计数器去控制
                if(watchedEvent.getState()== Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                    System.out.println(watchedEvent.getState());
                }
                if(watchedEvent.getType() == Event.EventType.NodeDataChanged){
                    //如果数据发生了变化
                    System.out.println("节点发送了变化，路径"+watchedEvent.getPath());
                }
            }
        });
        countDownLatch.await();
        System.out.println(zooKeeper.getState());
        // 创建了一个节点
        // 节点的路径  节点的值  ACL权限 节点的类型
//        zooKeeper.create("/enjoy1","enjoy".getBytes(),
//                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.getData("/enjoy1",true,null);
        zooKeeper.setData("/enjoy1","deer2".getBytes(),-1);

        zooKeeper.getData("/enjoy1",true,null);
        zooKeeper.setData("/enjoy1","deer1".getBytes(),-1);

        //获取数据
//        Stat stat = new Stat();
//        byte[] bytes = zooKeeper.getData("/enjoy1",true,stat);
//        System.out.println(new String(bytes));
//        System.out.println(stat);
//
//        //删除节点
////        zooKeeper.delete("/enjoy1",-1);
//
//        List<String> childrens = zooKeeper.getChildren("/enjoy1",true);
//        System.out.println(childrens);

    }
}
