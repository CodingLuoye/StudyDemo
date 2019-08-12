package com.study.zk.lock;

/**
 * @author YCKJ1409
 */
public class OrderService implements Runnable {

    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

//    /private Lock lock = new ZookeeperDistrbuteLock2();
    private Lock lock = new ZookeeperDistrbuteLock();


    @Override
    public void run() {
        getNumber();
    }

    public void getNumber(){
        try{
            lock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + "==="+number);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unLock();
        }
    }

    public static void main(String[] args) {
        System.out.println("#####生成唯一订单号");
        for (int i =0;i<100;i++){
            new Thread(new OrderService()).start();
        }
    }
}
