package com.study.zk.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author YCKJ1409
 */
public class ZookeeperDistrbuteLock2 extends ZookeeperAbstractLock {

    private CountDownLatch countDownLatch = null;
    /**
     * 当前请求的节点的前一个节点
     */
    private String beforePath;
    /**
     * 当前请求节点
     */
    private String currentPath;

    public ZookeeperDistrbuteLock2(){
        if(!this.zkClient.exists(PATH2)){
            this.zkClient.createPersistent(PATH2);
        }
    }

    @Override
    public boolean tryLock(){
        if(currentPath == null || currentPath.length()<=0){
            currentPath = this.zkClient.createEphemeralSequential(PATH2+"/","lock");
        }
        /**
         *         获取当前所有的临时节点饼排序，临时节点的名称为自增长的串如：000000400
         */
        List<String> childrens = this.zkClient.getChildren(PATH2);
        Collections.sort(childrens);
        if(currentPath.equals(PATH2+"/"+childrens.get(0))){
            return true;
        }else{
            /**
             * 如果当前节点不是排名第一，则取当前节点的前一个节点名称，并赋值给beforPath
             */
            int wz = Collections.binarySearch(childrens,currentPath.substring(7));
            beforePath = PATH2 + "/"+childrens.get(wz-1);
        }
        return false;
    }

    @Override
    public void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if(countDownLatch!=null){
                    countDownLatch.countDown();
                }
            }
        };
        /**
         * 给排在前面的节点增加数据删除的watcher,本项是启动另外一个线程去监听前置节点
         */
        this.zkClient.subscribeDataChanges(beforePath,listener);
        if(this.zkClient.exists(beforePath)){
            countDownLatch = new CountDownLatch(1);
            try{
                countDownLatch.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.zkClient.unsubscribeDataChanges(beforePath,listener);
    }

    @Override
    public void unLock(){
        zkClient.delete(currentPath);
        zkClient.close();
    }

}
