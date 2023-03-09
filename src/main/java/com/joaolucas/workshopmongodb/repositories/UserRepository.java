package com.joaolucas.workshopmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaolucas.workshopmongodb.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
}
