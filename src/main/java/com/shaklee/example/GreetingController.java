package com.shaklee.example;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(path = "/greeting", method = GET)
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") @NotNull String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}