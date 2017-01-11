package me.shinn.web

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

import javax.inject.Inject

@Configuration
@EnableWebSocket
@EnableScheduling
class WebSocketConfig implements WebSocketConfigurer {

    @Inject
    ArchivableUpdateHandler handler

    @Override
    void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/socket/archivable")
    }
}
