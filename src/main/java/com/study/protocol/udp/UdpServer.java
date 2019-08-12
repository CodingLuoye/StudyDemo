package com.study.protocol.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author YCKJ1409
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        //创建一个DatagramSocket实例，并且把实例绑定到本地地址上，端口10005
        DatagramSocket datagramSocket = new DatagramSocket(10005);
        byte bytes[] = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
        while(true){
            //接收到的数据包
            datagramSocket.receive(datagramPacket);
            //获取接收到的数据
            byte[] data = datagramPacket.getData();
            String str = new String(data,0,datagramPacket.getLength());
            if("88".equals(str)){
                break;
            }
            System.out.println("接收到的数据为："+str);
        }
        datagramSocket.close();
    }
}
