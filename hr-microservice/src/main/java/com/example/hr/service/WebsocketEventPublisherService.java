package com.example.hr.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.application.business.event.HrEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "use-websocket", havingValue = "true")
public class WebsocketEventPublisherService implements WebSocketHandler {
	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper;
	
	public WebsocketEventPublisherService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		var sessionId = session.getId();
		sessions.put(sessionId, session);
		System.err.println("New session is created with session id: %s".formatted(sessionId));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("An error has occured while transferring events: %s".formatted(e.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		var sessionId = session.getId();
		sessions.remove(sessionId );
		System.err.println("The session (%s) is closed.".formatted(sessionId));
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	@EventListener
	public void publish(HrEvent event) {
		sessions.values().forEach(session -> {
			try {
				session.sendMessage(new TextMessage(objectMapper.writeValueAsString(event)));
			} catch (Exception e) {
				System.err.println("Error while converting object to json: %s".formatted(e.getMessage()));
			}
		});
	}
}
