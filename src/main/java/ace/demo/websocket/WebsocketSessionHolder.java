/**
 * 
 */
package ace.demo.websocket;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

/**
 * @author lsk
 */
public abstract class WebsocketSessionHolder {

    public static final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<String, Session>();

    public static void put(Session session) {
        sessions.putIfAbsent(session.getId(), session);
    }

    public static Session get(String sessionId) {
        return sessions.get(sessionId);
    }

    public static Session remove(String sessionId) {
        return sessions.remove(sessionId);
    }

    public static Collection<Session> getAll() {
        return sessions.values();
    }

}
