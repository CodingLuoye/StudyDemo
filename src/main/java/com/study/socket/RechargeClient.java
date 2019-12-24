package com.study.socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * 双方采用Socket报文协议，同步短连接方式，json报文格式，UTF-8格式编码，明文方式（加密暂时不启用），传递报文
 * @author YCKJ1409
 * socket充值服务
 */
public class RechargeClient {

    private static Integer count = 10000;

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for(int i = 0;i<count;i++){
            TestThread testThread = new TestThread(String.valueOf(i),countDownLatch);
            testThread.start();
        }
    }

    static class TestThread extends Thread {

        private String name;

        private CountDownLatch countDownLatch;

        public TestThread(String name, CountDownLatch countDownLatch){
            this.name = name;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println(name+"客户端启动！！！");
                countDownLatch.countDown();
                String readline = "00133<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                        "<data>\n" +
                        "<tran_id>1156230000</tran_id>\n" +
                        "<cusno>13612182653</cusno>\n" +
                        "<amount></amount>\n" +
                        "<traceno></traceno>\n" +
                        "</data>\n";
                String inTemp = null;
                String turnLine = "\n";
                final String client = "Client:";
                int port = 8804;
                byte ipAddressTemp[] = {127, 0, 0, 1};
                InetAddress ipAddress = InetAddress.getByAddress(ipAddressTemp);
                //首先直接创建socket,端口号1~1023为系统保存，一般设在1023之外
                Socket socket = new Socket(ipAddress, port);
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
                countDownLatch.await();
                System.out.println(name+"客户端开始执行！！！");
                socketOut.println(readline);
                socketOut.flush();    //赶快刷新使Server收到，也可以换成socketOut.println(readline, ture)
                inTemp = socketIn.readLine();
                System.out.println(client + turnLine + inTemp);
                socketIn.close();
                socketOut.close();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
