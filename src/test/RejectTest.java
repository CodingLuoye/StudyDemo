import com.study.hystrix.HttpClientUtil;
import com.study.hystrix.EshopApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author YCKJ1409
 * 接口限流测试
 */
@SpringBootTest(classes = {EshopApplication.class})
@RunWith(SpringRunner.class)
public class RejectTest {

    @Test
    public void getData() {
        HttpClientUtil.sendGetRequest(
                "http://localhost:8081/getProductInfo?productId=1","UTF-8");
    }

    @Test
    public void getDatas() {
        HttpClientUtil.sendGetRequest(
                "http://localhost:8081/getProductInfos?productIds=1,2,3","UTF-8");
    }

    @Test
    public void getInterfaceProductInfo() {
        HttpClientUtil.sendGetRequest(
                "http://localhost:8081/getInterfaceProductInfo?productId=1","UTF-8");
    }

    @Test
    public void getCacheProduct() {
        HttpClientUtil.sendGetRequest(
                "http://localhost:8081/getCacheProduct?productId=1","UTF-8");
    }

    @Test
    public void getCityName() {
        HttpClientUtil.sendGetRequest(
                "http://localhost:8081/getCityName?productId=1","UTF-8");
    }

    @Test
    public void getProductInfos2(){
        HttpClientUtil.sendGetRequest(
                "http://localhost:8081/getProductInfos2?productIds=1,2,2,1,5","UTF-8");
    }

    @Test
    public void testReject() throws InterruptedException {
        for (int i = 0; i < 25; ++i) {
            new Thread(() -> HttpClientUtil.sendGetRequest("http://localhost:8081/getInterfaceProductInfo?productId=-2",
                    "UTF-8")).start();
        }
        // 防止主线程提前结束执行
        Thread.sleep(50000);
    }

    @Test
    public void testTimeout() {
        HttpClientUtil.sendGetRequest(
                "http://localhost:8081/getProductInfoTimeOut?productId=1","UTF-8");
    }
}