package com.group5.adminservice.repository;

import com.group5.adminservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface OrderRepository extends MongoRepository<Order, String>  {
}
