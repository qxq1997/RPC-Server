package Impl;

import common.entity.HelloObject;
import interfaces.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author by QXQ
 * @date 2021/2/28.
 */
public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String hello(HelloObject object) {
        logger.info("HelloServiceImpl服务端调用成功" + object.getGreeting());
        return "Impl" + object.getGreeting();
    }
}
