package server;

import Impl.HelloServiceImpl;
import interfaces.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registry.ServiceRegistry;
import registry.ServiceRegistryImpl;
import registry.ZkServiceRegistry;
import transport.RpcServer;
import transport.netty.NettyRpcServer;
import transport.socket.SocketRpcServer;

import java.net.InetSocketAddress;

/**
 * @author by QXQ
 * @date 2021/2/28.
 */
public class ServerTest {
    private static final Logger logger = LoggerFactory.getLogger(ServerTest.class);
    public static void main(String[] args) {
        ServiceRegistry registry = new ZkServiceRegistry();
        registry.registerService(HelloServiceImpl.class.getCanonicalName(), new InetSocketAddress("127.0.0.1", 8385));
        RpcServer server = new NettyRpcServer("127.0.0.1",8385);
        server.publishService(new HelloServiceImpl(), HelloService.class);
    }

}
