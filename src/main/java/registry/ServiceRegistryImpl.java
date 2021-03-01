package registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by QXQ
 * @date 2021/3/1.
 */
public class ServiceRegistryImpl implements ServiceRegistry{
    private final static Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    private final static Set<String> RegisteredSet = new ConcurrentHashMap<>().newKeySet();
    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistryImpl.class);

    public ServiceRegistryImpl() {

    }

    @Override
    public Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if(service == null){
            logger.error("#ServiceRegistryImpl#该服务未注册");
        }
        return service;
    }

    @Override
    public <T> void setService(T service) {
        String serviceName = service.getClass().getCanonicalName();
        if(serviceMap.containsValue(serviceName)){
            return;
        }
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if(interfaces.length == 0){
            logger.error(service + ",该服务未继承任何接口");
        }
        else {
            logger.info("注册服务：" + service.getClass().getCanonicalName());
        }
        for(Class<?> i : interfaces){
            serviceMap.put(i.getCanonicalName(), service);
        }

    }
}
