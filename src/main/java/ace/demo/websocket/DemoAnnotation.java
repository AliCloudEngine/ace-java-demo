/**
 * 
 */
package ace.demo.websocket;

import java.nio.ByteBuffer;
import java.util.Collection;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lsk
 */
@ServerEndpoint("/websocket/demo")
public class DemoAnnotation {

    private static final Logger logger = LoggerFactory.getLogger(DemoAnnotation.class);

    @OnOpen
    public void onOpen(Session session) throws Exception {
        session.getBasicRemote().sendText("open a new Websocket session");
        WebsocketSessionHolder.put(session);
        logger.info("open a new websocket session : " + session.getId());
    }

    @OnClose
    public void onClose(Session session) throws Exception {
        WebsocketSessionHolder.remove(session.getId());
        session.getBasicRemote().sendText("closed this session. bye~");
        session.close();
        logger.info("close a session : " + session.getId());
    }

    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) throws Exception {

        Collection<Session> sessions = WebsocketSessionHolder.getAll();
        
        for (Session s : sessions) {
            if (s.isOpen()) {
                s.getBasicRemote().sendText(msg);
            }
        }
    }

    @OnMessage
    public void echoBinaryMessage(Session session, ByteBuffer bb, boolean last) {
        // NO-OP
    }

    /**
     * Process a received pong. This is a NO-OP.
     * 
     * @param pm Ignored.
     */
    @OnMessage
    public void echoPongMessage(PongMessage pm) {
        // NO-OP
    }
}
