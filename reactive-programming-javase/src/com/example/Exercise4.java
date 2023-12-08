package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Exercise4 {
	private static final String BINANCE_REST_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private static final AtomicInteger counter = new AtomicInteger();
	public static void main(String[] args) throws InterruptedException, IOException {
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create(BINANCE_REST_API)).build();
		long start = System.currentTimeMillis();		
		for (var i=0;i<10;++i) {
			httpClient.sendAsync(request,BodyHandlers.ofString())
			          .thenAccept(response -> { 
			        	  System.err.println("[%s] %s".formatted(Thread.currentThread().getName(), response.body()));
			        	  if (counter.incrementAndGet()==10) {
			        		  long stop = System.currentTimeMillis();
			        		  System.err.println("Duration: %d ms.".formatted(stop-start));			        		  
			        	  }
			          });
		}
		System.out.println("All requests sent to the server!");
		TimeUnit.SECONDS.sleep(3);
	}

}
