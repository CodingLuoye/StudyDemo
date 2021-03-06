package com.study.zk.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;
/**
 * @author YCKJ1409
 */
public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {

    private CountDownLatch countDownLatch = null;

    @Override
    public boolean tryLock(){
        try {
            zkClient.createEphemeral(PATH);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void waitLock(){
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if(countDownLatch !=null){
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(PATH,iZkDataListener);
        if(zkClient.exists(PATH)){
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(PATH,iZkDataListener);

    }

    @Override
    public void unLock(){
        if(zkClient != null){
            zkClient.delete(PATH);
            zkClient.close();
            System.out.println("释放资源锁。。。");
        }
    }
}
