package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@RestController
public class MyRestController {

	@GetMapping
	@Bulkhead(name="run",type = Type.SEMAPHORE)
	public int getResource() {
		return 42;
	}
}
