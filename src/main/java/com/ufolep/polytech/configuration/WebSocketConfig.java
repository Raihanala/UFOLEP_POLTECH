package com.ufolep.polytech.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Configuration du broker de messages
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Les messages envoyés aux destinations commençant par /topic seront diffusés à tous les abonnés
        config.enableSimpleBroker("/topic");
        // Les messages envoyés par le client doivent commencer par /app ; ils seront ensuite dirigés vers les @MessageMapping du serveur
        config.setApplicationDestinationPrefixes("/app");
    }

    // Enregistrement des endpoints WebSocket auxquels le client se connectera
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // /ws est le point d'entrée pour la connexion WebSocket
        // withSockJS() active la prise en charge de SockJS pour la compatibilité avec les navigateurs qui ne supportent pas WebSocket
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}
