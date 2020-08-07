package com.casestudy.ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.casestudy.ss.model.Book;
import com.casestudy.ss.model.Subscription;
import com.casestudy.ss.repository.SubscriptionRepository;

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
	
	public String saveSubscriptions(List<Subscription> Subscriptions) {
		subscriptionRepository.saveAll(Subscriptions);
		return "All Books Saved Successfully";
	}
	
	public List<Subscription> getAllSubscriptions(){
		return subscriptionRepository.findAll();
	}

	public Book getBook(String bookId,int type) {
		Book book=null;
		switch(type) {

		case 1:
		 //to run case 1 remove @LoadBalanced Annotation from RestTemplate Bean
		 ServiceInstance instance = loadBalancerClient.choose("BookService"); 
		 String dynamicURI="http://"+instance.getHost()+":"+instance.getPort()+"/books/";
		 book =restTemplate.getForObject(dynamicURI+bookId, Book.class);
		 System.out.println(book.toString());
		 return book;

		case 2:
			//to run case 2 add @LoadBalanced Annotation from RestTemplate Bean
			String URL="http://BookService/books/"+bookId; 
			book =restTemplate.getForObject(URL, Book.class); 
			return book;

		default:
			//to run default case remove @LoadBalanced Annotation from RestTemplate Bean
			book = restTemplate.getForObject(booksServicePath+bookId, Book.class);
			return book;
		}
		
	}
	
}
