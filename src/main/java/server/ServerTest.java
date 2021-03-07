package server;

import Impl.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registry.ServiceRegistry;
import registry.ServiceRegistryImpl;
import transport.RpcServer;
import transport.netty.NettyRpcServer;
import transport.socket.SocketRpcServer;

/**
 * @author by QXQ
 * @date 2021/2/28.
 */
public class ServerTest {
    private static final Logger logger = LoggerFactory.getLogger(ServerTest.class);
    public static void main(String[] args) {
        ServiceRegistry registry = new ServiceRegistryImpl();
        registry.setService(new HelloServiceImpl());
        RpcServer server = new NettyRpcServer();
        server.start(8385);
    }

}
