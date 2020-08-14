package com.casestudy.ss.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.casestudy.ss.model.BookUser;

public interface BookUserRepository extends MongoRepository<BookUser,String>{
}
