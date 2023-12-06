package com.example.binance.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BinanceRestAsyncClient {
	private static final String BINANCE_REST_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private static final AtomicInteger counter = new AtomicInteger();
	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create(BINANCE_REST_API)).build();
		var start = System.currentTimeMillis();
		var pool = Executors.newFixedThreadPool(20);
		for (var i=0;i<20;++i) {
			httpClient.sendAsync(request,BodyHandlers.ofString())
					  .thenAcceptAsync(response -> {
						  System.err.println(response.body());
						  if(counter.incrementAndGet() == 20) {
							  var stop = System.currentTimeMillis();
							  System.err.println("Duration: %d ms.".formatted((stop-start)));							  
						  }
						  
					  },pool);
		}
		TimeUnit.SECONDS.sleep(5);
		pool.shutdown();
	}

}
