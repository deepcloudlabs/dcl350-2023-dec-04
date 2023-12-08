package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class Exercise03 {
	private static final String BINANCE_REST_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

	public static void main(String[] args) throws InterruptedException, IOException {
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create(BINANCE_REST_API)).build();
		long start = System.currentTimeMillis();
		for (var i=0;i<10;++i) {
			var response = httpClient.send(request,BodyHandlers.ofString()).body();
			System.err.println(response);
		}
		long stop = System.currentTimeMillis();
		System.err.println("Duration: %d ms.".formatted(stop-start));
	}

}
