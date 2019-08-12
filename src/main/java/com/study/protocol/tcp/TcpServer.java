package com.study.protocol.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author YCKJ1409
 */
public class TcpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("TCP服务已经启动，端口是8888");
        while(true){
            //等待客户端的请求，如果有请求分配一个socket
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String buffer =null;
            //循环读取输入的每一行数据
            while((buffer=reader.readLine())!=null && !buffer.equals("")){
                System.out.println(buffer);
            }
            //通过socker对象得到输出流，构造BufferedWrite对象
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //模拟了http头协议
            writer.write("HTTP/1.1 200 OK \r\n Content-Type:text/html \r\n charset=UTF-8 \r\n\r\n");
            writer.write("<html><head><title>http请求</title><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/></head><body><h1>这是一个http请求</h1></body></html>");
            writer.flush();
            reader.close();
            writer.close();
            socket.close();
        }
    }
}
