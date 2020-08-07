package com.example.bs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bs.model.Book;
import com.example.bs.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookrepository;

	public Optional<Book> getBookById(String bookId) {
		return bookrepository.findById(bookId);
	}

	public List<Book> getAllBooks() {
		return bookrepository.findAll();
	}

	public String saveAllBooks(List<Book> books) {
		bookrepository.saveAll(books);
		return "All Books Saved Successfully";
	}
	
	public Book updateBooksAvailabilty(String bookId,int incremental_count) {
		Book book=bookrepository.findById(bookId).get();
		int copiesavailable=book.getCopiesAvailable()+incremental_count;
		if(copiesavailable<=book.getTotalCopies())
		book.setCopiesAvailable(copiesavailable);
		bookrepository.save(book);
		return book;
	}


}
