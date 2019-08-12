package com.study.controller;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;

@Controller
public class NobleMetalController {

    private static Logger logger = LoggerFactory.getLogger(NobleMetalController.class);

    @GetMapping("/nobleMetal")
    public String stock(){
        return "nobleMetal";
    }

    @RequestMapping(value = "/needPrice",produces = "text/event-stream;")
    @ResponseBody
    public String push() {
        Random r = new Random();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return makeResp(r);
    }

    @RequestMapping(value = "needPrice2")
    public void pushRight(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        Random r = new Random();
        try {
            PrintWriter pw = response.getWriter();
            int i = 0;
            while(i<10){
                if(pw.checkError()){
                    System.out.println("客户端断开连接");
                    return;
                }
                Thread.sleep(1000);
                pw.write(makeResp(r));
                pw.flush();
                i++;
            }
            System.out.println("达到阈值，结束发送。。。");
            pw.write("data:stop\n\n");
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String makeResp(Random random){
        String str = "我是随机的数："+random.nextInt(10);
        return str;
    }

}
