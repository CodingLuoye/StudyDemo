package com.study.protocol.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author YCKJ1409
 *
 */
public class TcpClient {

    public static void main(String[] args) throws IOException {
        String msg = "hello 13技师";
        //创建一个Socket
        Socket socket = new Socket("127.0.0.1",8888);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(msg);
        printWriter.flush();
        printWriter.close();
        socket.close();

    }

}
