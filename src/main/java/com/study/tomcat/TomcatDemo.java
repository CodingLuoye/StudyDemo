package com.study.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 *
 * 手写tomcat启动
 * @author YCKJ1409
 */
public class TomcatDemo {

    public static void main(String[] args) throws LifecycleException {
        String classPath = System.getProperty("user.dir");
        System.out.println(classPath);
        Tomcat tomcat = new Tomcat();
        Connector connector = tomcat.getConnector();
        connector.setPort(9090);
        Host host = tomcat.getHost();
        host.setName("localhost");
        host.setAppBase("webapps");
        Context context = tomcat.addContext(host,"/",classPath);
        if(context instanceof StandardContext){
            StandardContext standardContext = (StandardContext) context;
            String webXmlPath = classPath + "src\\main\\java\\com\\study\\tomcat\\web.xml";
            standardContext.setDefaultContextXml(webXmlPath);
//            Wrapper wrapper = tomcat.addServlet("/","demoServlet",new DemoServlet());
//            wrapper.addMapping("/Test");
        }
        // tomcat 跑起来
        tomcat.start();
        //强制tomcat，避免main方法执行结束后关闭
        tomcat.getServer().await();
    }
}
