package io.github.rxcats.webfluxwebsocketdemo.config;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        Flux<WebSocketMessage> flux = webSocketSession.receive()
            .map(message -> webSocketSession.textMessage("echo:" + message.getPayloadAsText()));
        return webSocketSession.send(flux);
    }
}
