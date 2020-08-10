package com.example.bs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bs.model.Book;
import com.example.bs.repository.BookRepository;

@Service
public class BookService {

	private final Producer producer;

	@Autowired
	public BookService(Producer producer) {
		this.producer = producer;
	}

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

	public Book updateBooksAvailabilty(String bookId, String userActivity) {

		Book book = bookrepository.findById(bookId).get();

		if (userActivity.equalsIgnoreCase("subscribebook")) {

			int copiesavailable = book.getCopiesAvailable() - 1;
			if (copiesavailable <= book.getTotalCopies())
				book.setCopiesAvailable(copiesavailable);
			bookrepository.save(book);
			return book;

		} else if (userActivity.equalsIgnoreCase("returnbook")) {
			System.out.println("here");
			int copiesavailable = book.getCopiesAvailable() + 1;
			if (copiesavailable <= book.getTotalCopies())
				book.setCopiesAvailable(copiesavailable);
			bookrepository.save(book);

			this.producer.sendMessage(book.getBookName() + "Book is now available please go ahead to subscribe it");

			
			
			return book;
		} else {
			return book;
		}

	}

}
