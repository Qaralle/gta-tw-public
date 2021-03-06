package itmo.p33101.gtav_territorywar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        //должны быть направлены в методы, занимающиеся обработкой сообщений.
//        registry.setApplicationDestinationPrefixes("/api/app/notification");
        //должны быть направлены в брокер сообщений. Брокер перенаправляет сообщения всем клиентам, подписанным на тему
        registry.enableSimpleBroker("/notification");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/app/notifications").withSockJS();

    }
}