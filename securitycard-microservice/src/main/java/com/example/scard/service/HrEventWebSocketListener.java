package com.example.scard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import jakarta.annotation.PostConstruct;

@Service
public class HrEventWebSocketListener implements WebSocketHandler{
	private static final String HR_REST_WS_API = "ws://localhost:9100/hr/api/v1/events";
	private final WebSocketClient websocketClient; 
	
	public HrEventWebSocketListener(WebSocketClient websocketClient) {
		this.websocketClient = websocketClient;
	}

	@PostConstruct
	public void connect() {
		websocketClient.execute(this, HR_REST_WS_API);
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the hr microservice: %s".formatted(session.getId()));		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("New event has arrived from websocket: %s".formatted(message.getPayload()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
