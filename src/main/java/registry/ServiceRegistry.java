package registry;

/**
 * @author by QXQ
 * @date 2021/3/1.
 */
public interface ServiceRegistry {
    Object getService(String serviceName);
    <T> void setService(T service);
}
