/**
 * 
 */
package ace.demo.websocket;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lsk
 */
public class DemoWebsocketConfig implements ServerApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(DemoWebsocketConfig.class);

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
        return null;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        for (Class<?> clazz : scanned) {
            logger.info(" scanned class : " + clazz);
        }
        return scanned;
    }

}
