package com.casestudy.ss.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.casestudy.ss.model.Subscription;

@Component
public class SubscriptionDBQueries {
	

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Subscription> findByBookIdAndSubscriberName(String bookId,String subscriberName){
		Query query = new Query();
		//query.addCriteria(Criteria.where("bookId").is(bookId));
		query.addCriteria(
			    new Criteria().andOperator(
			        Criteria.where("bookId").is(bookId),
			        Criteria.where("subscriberName").is(subscriberName)
			    )
			);
		return mongoTemplate.find(query, Subscription.class);
	}

}
