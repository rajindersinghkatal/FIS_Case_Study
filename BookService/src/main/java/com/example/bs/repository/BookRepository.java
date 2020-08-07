package com.example.bs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.bs.model.Book;

public interface BookRepository extends MongoRepository<Book,String> {

}
