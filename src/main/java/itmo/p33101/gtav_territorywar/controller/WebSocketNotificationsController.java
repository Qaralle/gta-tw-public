package itmo.p33101.gtav_territorywar.controller;

import itmo.p33101.gtav_territorywar.messages.Message;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class WebSocketNotificationsController {
    private final SimpMessagingTemplate template;

    public WebSocketNotificationsController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void characteristic() throws Exception {
        Message aaa = new Message("aaaaaaaa");
        template.convertAndSend("/notification/characteristic",aaa);
    }

    public void map() throws Exception {
        Message aaa = new Message("aaaaaaaa");
        template.convertAndSend("/notification/map",aaa);
    }
}
