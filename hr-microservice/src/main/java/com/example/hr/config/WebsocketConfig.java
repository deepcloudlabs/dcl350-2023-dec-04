package com.example.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.WebsocketEventPublisherService;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer{

	private final WebsocketEventPublisherService websocketEventPublisherService;
	
	public WebsocketConfig(WebsocketEventPublisherService websocketEventPublisherService) {
		this.websocketEventPublisherService = websocketEventPublisherService;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(websocketEventPublisherService, "/events")
		        .setAllowedOrigins("*");
		
	}

}
