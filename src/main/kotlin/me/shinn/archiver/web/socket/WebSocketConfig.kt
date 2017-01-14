package me.shinn.archiver.web.socket

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

import javax.inject.Inject

@Configuration
@EnableWebSocket
@EnableScheduling
open class WebSocketConfig (val handler: ArchivableUpdateHandler) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(handler, "/socket/archivable")
    }
}
