package com.anirudh.senddirect.websocket;

import com.anirudh.senddirect.models.SignalMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SignalController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public SignalMessage sendMessage(SignalMessage message){
        return message;
    }
}
