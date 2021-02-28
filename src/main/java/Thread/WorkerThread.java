package Thread;

import entity.RpcRequest;
import entity.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import Enum.ResponseCode;

/**
 * @author by QXQ
 * @date 2021/2/28.
 */
public class WorkerThread implements Runnable{
    private Object service;
    private Socket socket;

    public WorkerThread(Socket socket, Object service) {
        this.service = service;
        this.socket = socket;
    }

    public WorkerThread() {
    }

    @Override
    public void run() {
        try(ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())){
            RpcRequest rpcRequest = (RpcRequest)inputStream.readObject();
            Method method = service.getClass().getMethod(rpcRequest.getMethod(),rpcRequest.getParamTypes());
            String ans = (String) method.invoke(service, rpcRequest.getParameters());
            RpcResponse response = new RpcResponse();
            response.setData(ans);
            response.setCode(ResponseCode.SUCCESS.getCode());
            outputStream.writeObject(response);
            outputStream.flush();
        }
        catch (InvocationTargetException | IllegalAccessException | IOException | ClassNotFoundException | NoSuchMethodException e){

        }
    }
}
