package com.casestudy.ss.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.casestudy.ss.model.Subscription;

public interface SubscriptionRepository extends MongoRepository<Subscription,String> {

}

