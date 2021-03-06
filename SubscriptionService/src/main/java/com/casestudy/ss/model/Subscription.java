package com.casestudy.ss.model;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Subscription")
public class Subscription {
	
	@Id
	String _id;
	String subscriberName;
	String bookId;
	LocalDate dateSubscribed;
	LocalDate dateReturned;
    String notify;
}
