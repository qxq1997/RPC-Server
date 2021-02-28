package server;

import Impl.HelloServiceImpl;
import Interface.HelloService;
import Listener.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author by QXQ
 * @date 2021/2/28.
 */
public class ServerTest {
    private static final Logger logger = LoggerFactory.getLogger(ServerTest.class);
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(new HelloServiceImpl(), 9000);
        logger.info("server启动");
    }

}
