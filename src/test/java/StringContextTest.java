import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yury on 7/28/16.
 */
@ContextConfiguration("classpath:spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class StringContextTest {

    @Test
    public void contextTest() throws InterruptedException {
        Thread.sleep(100L);
    }
}
