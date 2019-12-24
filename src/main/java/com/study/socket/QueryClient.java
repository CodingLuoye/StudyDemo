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
 * socket查询服务
 */
public class QueryClient {

    private static Integer count = 100;

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
                countDownLatch.countDown();
                System.out.println(name+"客户端启动！！！");
                countDownLatch.await();
                System.out.println(name+"客户端开始执行！！！");
                String readline = "{\"Header\": {\"TranCode\": \"56230002\",\"SCode\": \"104\",\"TCode\": " +
                        "\"00000001\",\"OpNo\": \"操作员号\",\"TermId\": \"终端编号\",\"TranTime\": \"操作日期时间\",\"TraceNo\": " +
                        "\"交易流水号\"},\"Body\": {\"IdType\": \"01\",\"IdNo\": \"420115199306054013\",\"Name\": " +
                        "\"1345\",\"CustCodeType\": 1,\"CustCode\": \"15623193802\"}}";
                String inTemp = null;
                String turnLine = "\n";
                final String client = "Client:";
                int port = 4000;
                byte ipAddressTemp[] = {127, 0, 0, 1};
                InetAddress ipAddress = InetAddress.getByAddress(ipAddressTemp);
                //首先直接创建socket,端口号1~1023为系统保存，一般设在1023之外
                Socket socket = new Socket(ipAddress, port);
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
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
