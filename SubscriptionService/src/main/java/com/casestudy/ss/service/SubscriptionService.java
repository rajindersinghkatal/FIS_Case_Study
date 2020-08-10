package com.casestudy.ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.casestudy.ss.model.Book;
import com.casestudy.ss.model.Subscription;
import com.casestudy.ss.repository.SubscriptionRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class SubscriptionService {
	
	@Value("${BookService.path}")
	private String booksServicePath;
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	public List<Subscription> getAllSubscriptions(){
		return subscriptionRepository.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "fallBack_subscribeBook", commandProperties = {  
			  @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			  @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold", value="3"),
			  @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds", value="10000"),
			  @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage", value="50"),
			  @HystrixProperty(name ="metrics.rollingStats.timeInMilliseconds", value="10000")
			  }) 
	public String subscribeBook(Subscription subscription) {
		
		String result="";
		String URL="http://BookService/books/"+subscription.getBookId();
		Book book =restTemplate.getForObject(URL, Book.class);
		
		if(book.getCopiesAvailable()<book.getTotalCopies()) {
			
			URL="http://BookService/books/UpdateAvailability/"+subscription.getBookId()+"/"+"subscribebook";
			restTemplate.postForObject(URL,subscription,Book.class);
			subscriptionRepository.save(subscription);
			result="Book Subscribed Successfully";
		
		}else {
			
			result="NO Copies available for this book, Please try again later";
		}
		return result;
	}

	
	public String fallBack_subscribeBook(Subscription subscription) {
		return "Book Service is down right now!!! So Cannot subscribe the book!!";
	}
	
	@HystrixCommand(fallbackMethod = "fallBack_returnBook", commandProperties = {  
			  @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			  @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold", value="3"),
			  @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds", value="10000"),
			  @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage", value="50"),
			  @HystrixProperty(name ="metrics.rollingStats.timeInMilliseconds", value="10000")
			  }) 
	public String returnBook(Subscription subscription) {
		
		String result="";
		String URL="http://BookService/books/"+subscription.getBookId();
		Book book =restTemplate.getForObject(URL, Book.class);
		
		if(book.getCopiesAvailable()<book.getTotalCopies()) {
			
			URL="http://BookService/books/UpdateAvailability/"+subscription.getBookId()+"/"+"returnbook";
			restTemplate.postForObject(URL,subscription,Book.class);
			subscriptionRepository.save(subscription);
			result="Book Subscribed Successfully";
		
		}else {
			
			result="NO Copies available for this book, Please try again later";
		}
		return result;
	}
	
	public String fallBack_returnBook(Subscription subscription) {
		return "Book Service is down right now!!! So Cannot return the book!!";
	}

	
}
