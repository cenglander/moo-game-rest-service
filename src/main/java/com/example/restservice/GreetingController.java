package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@PostMapping("/greetingpost")
	public ResponseEntity<Greeting> greetingPost(@RequestBody String name) {
		return ResponseEntity.accepted().
				body(new Greeting(counter.incrementAndGet(), String.format(template, name)));
	}
	
	@PostMapping("/greetingpost/json")
	public ResponseEntity<Greeting> greetingPostJson(@RequestBody Greeting greeting) {
		return ResponseEntity.accepted().
				body(new Greeting(greeting.getId(), String.format(template, greeting.getContent())));
	}
	
	
//	 @PostMapping("/adder")  // localhost:8080/adder  + JSON i POST requestbody
//	    public ResponseEntity<UlfsInt> add3(@RequestBody IntegerPair p ) {
//	        return ResponseEntity.accepted().
//	        body(new UlfsInt(adderService.add(p.getFirst() , p.getSecond())));
//	 }

	
}
