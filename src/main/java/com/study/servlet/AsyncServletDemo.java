package com.study.servlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YCKJ1409
 * 异步Servlet加上DeferedResulst用法
 */
public class AsyncServletDemo {

    private final String[] news = new String[]{"I am jack chen","you are stupio","I love you"};

    /**
     * 线程池的资源管理
     */
    static class listener implements ServletContextListener{

        @Override
        public void contextInitialized(ServletContextEvent sce) {

        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {

        }
    }

    class Work implements Runnable{

        AsyncContext asyncContext;

        public Work(AsyncContext asyncContext){
            this.asyncContext = asyncContext;
        }
        @Override
        public void run() {

        }
    }
    class AsyncListenerImpl implements AsyncListener{

        @Override
        public void onComplete(AsyncEvent event) throws IOException {

        }

        @Override
        public void onTimeout(AsyncEvent event) throws IOException {

        }

        @Override
        public void onError(AsyncEvent event) throws IOException {

        }

        @Override
        public void onStartAsync(AsyncEvent event) throws IOException {

        }
    }

    @WebServlet(asyncSupported = true)
    class AsyncBusiServlet extends HttpServlet{
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doGet(req, resp);
            //启动异步
            AsyncContext asyncContext = req.startAsync();
            asyncContext.addListener(new AsyncListenerImpl());
            executorService.execute(new Work(asyncContext));
        }
    }

    @RequestMapping("/pushnews")
    public String news(){
        return "pushnews";
    }

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    @RequestMapping(value = "/realtimeNews")
    @ResponseBody
    public DeferredResult<String> realtimeNews(){
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int index = new Random().nextInt(news.length);
                deferredResult.setResult(news[index]);
            }
        });
        return deferredResult;
    }

}
