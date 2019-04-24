package zv2.com.cn.log;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 *
 * @author liubao
 * @date 2019/4/24 1:25
 */
public class Log4jTest {
    private Logger logger = Logger.getLogger(Log4jTest.class);
    @Test
    public void test1() {
        logger.fatal("fatal message..");
        logger.error("error message");
        logger.warn("warn message");
        logger.info("info message");
        logger.debug("debug message");
        logger.trace("trace message");
    }
}
