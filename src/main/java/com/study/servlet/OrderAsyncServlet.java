package com.study.servlet;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YCKJ1409
 * test servlet
 * xml启用异步处理支持,默认为false
 * <servlet>
 *     <servlet-name>DemoServlet</servlet-name>
 *     <servlet-class>footmark.servlet.Demo Servlet</servlet-class>
 *     <async-supported>true</async-supported>
 * </servlet>
 * 注解启用异步处理支持
 * @WebServlet(value = "/orderAsync",asyncSupported = true)
 *
 */
@WebServlet(value = "/orderAsync",asyncSupported = true)
public class OrderAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("主线程"+Thread.currentThread() + "===》start..." +System.currentTimeMillis());
        AsyncContext startAsycn = req.startAsync();
        startAsycn.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("副线程"+Thread.currentThread() + "===》start..." +System.currentTimeMillis());
                    sayHello();
                    AsyncContext asyncContext = req.getAsyncContext();
                    ServletResponse response = asyncContext.getResponse();
                    response.getWriter().println("hello...async");
                    startAsycn.complete();
                    System.out.println("副线程"+Thread.currentThread() + "===》end..." +System.currentTimeMillis());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        System.out.println("主线程"+Thread.currentThread() + "===》end..." +System.currentTimeMillis());
    }

    private void sayHello() throws Exception {
        System.out.println(Thread.currentThread()+"process...");
        Thread.sleep(3000);
    }
}
