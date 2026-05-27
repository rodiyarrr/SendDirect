package com.anirudh.senddirect.websocket;

import com.anirudh.senddirect.models.SignalMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SignalController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(SignalMessage message) {
        System.out.println("Received: " + message.getContent());

        messagingTemplate.convertAndSend("/topic/session/"
                        + message.getShareCode(), message);

    }

}