package com.study.tool;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class QueryLog {
//    public static void main(String[] args) throws IOException {
//        FileWriter writer=new FileWriter("D:\\交付\\1.log");
//        FileReader fr=new FileReader("D:\\交付\\info (1).log");
//        BufferedReader br=new BufferedReader(fr);
//        String line="";
//        while ((line=br.readLine())!=null) {
//            if(line.contains("收到一网通收款回调接口通知") || line.contains("招行收款接口响应数据")){
//                writer.write(line + "\r\n");
//            }
//        }
//        br.close();
//        fr.close();
//        writer.close();
//    }
    public static void main(String[] args) {
        Random random = new Random();

        try {
            Thread.sleep(random.nextInt(10)*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(random.nextInt(10));
    }
}
