package thread;

import entity.RpcRequest;
import entity.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import enums.ResponseCode;
import handler.RequestHandler;

import registry.ServiceRegistry;

/**
 * @author by QXQ
 * @date 2021/2/28.
 */
public class WorkerThread implements Runnable{
    private Socket socket;
    private ServiceRegistry registry;
    private RequestHandler requestHandler;

    public WorkerThread(Socket socket, ServiceRegistry registry, RequestHandler requestHandler) {
        this.socket = socket;
        this.registry = registry;
        this.requestHandler = requestHandler;
    }
    @Override
    public void run() {
        try(ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())){
            RpcRequest rpcRequest = (RpcRequest)inputStream.readObject();
            String serviceName = rpcRequest.getInterfaceName();
            Object service = registry.getService(serviceName);
            RpcResponse response = requestHandler.handle(rpcRequest, service);
            outputStream.writeObject(response);
            outputStream.flush();
        }
        catch ( IOException | ClassNotFoundException  e){

        }
    }
}
