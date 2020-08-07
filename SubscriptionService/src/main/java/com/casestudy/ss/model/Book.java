package com.casestudy.ss.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Book")
public class Book {
	
	@Id
	String bookId;
	String bookName;
	String author;
	int copiesAvailable;
	int totalCopies;

	

}
