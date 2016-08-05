import org.apache.logging.log4j.core.util.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

/**
 * Created by yury on 7/28/16.
 */
@ContextConfiguration("classpath:spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class StringContextTest {

    @Test
    public void contextTest() throws InterruptedException, IOException {
        File a = new File("mydir");
        FileUtils.mkdir(a,true);
    }
}
