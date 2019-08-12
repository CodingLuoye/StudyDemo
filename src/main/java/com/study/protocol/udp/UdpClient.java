package com.study.protocol.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author YCKJ1409
 *
 */
public class UdpClient {

    public static void main(String[] args) throws Exception {
        //创建一个DatagramSocket实例
        DatagramSocket datagramSocket = new DatagramSocket();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = bufferedReader.readLine())!=null){
            byte [] bytes = line.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("127.0.0.1"),10005);
            datagramSocket.send(datagramPacket);
            if("88".equals(line)){
                break;
            }
        }
        datagramSocket.close();
    }

}
