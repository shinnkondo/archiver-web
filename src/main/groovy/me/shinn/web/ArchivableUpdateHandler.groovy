package me.shinn.web

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

import javax.inject.Named

@Named
class ArchivableUpdateHandler extends TextWebSocketHandler {
    WebSocketSession session

    void update() {
        session.sendMessage(new TextMessage("Update available"));
    }

    @Override
    void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Connection established");
        this.session = session;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            session.close();
        } else {
            System.out.println("Received:" + message.getPayload());
        }
    }

}
