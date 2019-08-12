
import com.study.hystrix.HttpClientUtil;
import com.study.hystrix.EshopApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {EshopApplication.class})
@RunWith(SpringRunner.class)
public class CircuitBreakerTest {

    @Test
    public void testCircuitBreaker() throws InterruptedException {
        String baseURL = "http://localhost:8081/getBreakerProductInfo?productId=";

        for (int i = 0; i < 30; ++i) {
            // 传入-1，会抛出异常，然后走降级逻辑
            HttpClientUtil.sendGetRequest(baseURL + "-1","UTF-8");
        }

        Thread.sleep(3000);
        System.out.println("After sleeping...");

        for (int i = 31; i < 100; ++i) {
            // 传入1，走服务正常调用
            HttpClientUtil.sendGetRequest(baseURL + "1","UTF-8");
        }
    }
}