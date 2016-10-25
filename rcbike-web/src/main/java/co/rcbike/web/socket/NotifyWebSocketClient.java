package co.rcbike.web.socket;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ApplicationScoped
public class NotifyWebSocketClient {

    private Map<String, Session> sessionClient = new ConcurrentHashMap<>();

    public void addSession(String email, Session session) {
        sessionClient.put(email, session);
    }

    public void removeSession(String email) {
        sessionClient.remove(email);
    }

    @SuppressWarnings("rawtypes")
    public void notifyClient(String email, MessageWrapper msg) throws JsonProcessingException {
        Session session = sessionClient.get(email);
        if (session != null) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(msg);
            session.getAsyncRemote().sendText(json);
        }
    }

    @SuppressWarnings("serial")
    public static class MessageWrapper<T> implements Serializable {

        private T content;
        private String msgType;

        public MessageWrapper(String msgType, T content) {
            super();
            this.content = content;
            this.msgType = msgType;
        }

        public T getContent() {
            return content;
        }

        public String getMsgType() {
            return msgType;
        }

    }
}
