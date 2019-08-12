import com.study.BlogProperties;
import com.study.WeixinWebApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WeixinWebApplication.class)
public class ApplicationTest {

    @Autowired
    private BlogProperties blogProperties;

    @Test
    public void test(){
        Assert.assertEquals(blogProperties.getName(),"didi");
        Assert.assertEquals(blogProperties.getTitle(),"Spring Boot Learning");
    }
}
