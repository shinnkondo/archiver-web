package me.shinn.archiver.web.socket

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

import javax.inject.Named

@Named
class ArchivableUpdateHandlerImpl : ArchivingJobUpdateNotifier, TextWebSocketHandler() {
    var session: WebSocketSession? = null

    override fun update() {
        session?.sendMessage(TextMessage("Update available"));
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        System.out.println("Connection established");
        this.session = session
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        if ("CLOSE" == message.payload) {
            session.close()
        } else {
            System.out.println("Received:" + message.getPayload());
        }
    }

}
