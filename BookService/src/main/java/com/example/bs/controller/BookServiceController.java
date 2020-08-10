package com.example.bs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bs.configuration.GreetingConfiguration;
import com.example.bs.model.Book;
import com.example.bs.service.BookService;

@RestController
@RequestMapping("/books")
public class BookServiceController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired 
	GreetingConfiguration greetingConfiguration;
	
	@Value("${greeting:greetingalternate}")
	private String greet;
	
	@Value("${greeting.message:Hello this is default message because config servr is not running}")
	private String greetingmessage;
	
	@Value("${greeting.name:Hello this is default name because config servr is not running}")
	private String greetname;
	

	@GetMapping("/greeting/{type}")
	public String greet1(@PathVariable int type) {
		switch(type){
			case 1:return greet;
			case 2:return greetingConfiguration.getName()+"::"+greetingConfiguration.getMessage();
			case 3:return greetname+"::"+greetingmessage;
			default:return greet;
		}
	}
	
	@GetMapping(value="/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Book> getBookById(@PathVariable String bookId) {
		return bookService.getBookById(bookId);
	}
	
	
	
	@GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@PostMapping(value="/savebooks",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String saveAllBooks(@RequestBody List<Book> books) {
		return bookService.saveAllBooks(books);
	}
	
	//type can be subscribebook or returnbook
	@PostMapping(value="/UpdateAvailability/{bookId}/{userActivity}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Book updateBooksAvailabilty(@PathVariable String bookId,@PathVariable String userActivity) {
		return bookService.updateBooksAvailabilty(bookId,userActivity);
	}
}
