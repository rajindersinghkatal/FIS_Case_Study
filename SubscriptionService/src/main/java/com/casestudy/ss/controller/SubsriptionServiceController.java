package com.casestudy.ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.ss.model.Book;
import com.casestudy.ss.model.Subscription;
import com.casestudy.ss.service.SubscriptionService;

@RestController
public class SubsriptionServiceController {
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@GetMapping(value="/subscription",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Subscription> getAllSubscriptions(){
		return subscriptionService.getAllSubscriptions();
	}
		
	@PostMapping(value="/subscription/savesubscriptions",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String saveSubscriptions(@RequestBody List<Subscription> Subscriptions) {
		return subscriptionService.saveSubscriptions(Subscriptions);
	}
		
	@GetMapping(value="/subscription/getbook/{bookId}/{type}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Book getBook(@PathVariable String bookId,@PathVariable int type) {
		return subscriptionService.getBook(bookId,type);
	}
	


}
