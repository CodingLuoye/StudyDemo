package com.study.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YCKJ1409
 * test servlet
 */
@WebServlet("/order")
public class JamesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(Thread.currentThread() + "start...");
        try {
            sayHello();
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.getWriter().println("hello...");
        System.out.println(Thread.currentThread() + "end...");

    }

    private void sayHello() throws Exception {
        System.out.println(Thread.currentThread()+"process...");
        Thread.sleep(3000);
    }
}
