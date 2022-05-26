package com.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {
    @MessageMapping("/spring")
    @SendTo("/boot/websocket")
    public String broadcastNews (@Payload String message) {
        return message;
    }
}
