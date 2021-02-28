package Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import Thread.WorkerThread;

/**
 * @author by QXQ
 * @date 2021/2/28.
 */
public class RpcServer {

    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private final ExecutorService pool ;

    public RpcServer() {
        ThreadFactory factory = Executors.defaultThreadFactory();
        int coreNum = 5;
        int maxNum = 50;
        int keepAliveTime = 60;
        BlockingQueue<Runnable> workingQueue= new ArrayBlockingQueue<>(100);
        pool = new ThreadPoolExecutor(coreNum, maxNum,keepAliveTime, TimeUnit.SECONDS, workingQueue, factory);
    }
    public void register(Object service, int port){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            Socket socket;
            while((socket = serverSocket.accept()) != null){
                logger.info("收到客户端连接，socker:" + socket.toString());
                pool.execute(new WorkerThread(socket, service));
            }
        }
        catch (IOException e){
            logger.error("#RpcServer.register#连接失败");
        }
    }
}
