package com.example.binance.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class HrMicroserviceWebSocketClient {

	private static final String HR_REST_WS_API = "ws://localhost:9100/hr/api/v1/events";

	public static void main(String[] args) throws InterruptedException {
		var listener = new HrMicroserviceWebSocketListener();
		HttpClient.newHttpClient()
				  .newWebSocketBuilder()
				  .buildAsync(URI.create(HR_REST_WS_API), listener );
		TimeUnit.MINUTES.sleep(20);
	}

}

//Listener/Observer
class HrMicroserviceWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the hr microservice instance!");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the binance server!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("An error has occurred:"+error.getMessage());
	}
	
}

