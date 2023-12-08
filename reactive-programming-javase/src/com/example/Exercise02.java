package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise02 {
	public static void main(String[] args) {
		System.out.println("Application is just started.");
		C c= new C();
		c.gun()      // callback function -> observer pattern
		 .thenAccept(result -> System.out.println("Result is %d".formatted(result))); // HoF
		for (var i=1;i<1_000;i++) {
			try {TimeUnit.MILLISECONDS.sleep(10);}catch(Exception e) {}
			System.out.println("Application is processing data [%d]".formatted(i));
		}
		System.out.println("Application is done.");
	}
	
}

class C {
	public int fun() { // synchronous
		try {TimeUnit.SECONDS.sleep(5);}catch(Exception e) {}
		return 42;
	}
	public CompletableFuture<Integer> gun() { // asynchronous
		return CompletableFuture.supplyAsync(() -> { // submits the task to a thread pool
			System.out.println("%s runs gun()...".formatted(Thread.currentThread().getName()));
			try {TimeUnit.SECONDS.sleep(5);}catch(Exception e) {}
			return 42;			
		},Executors.newFixedThreadPool(10));
	}
}
