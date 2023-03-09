package com.joaolucas.workshopmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaolucas.workshopmongodb.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
}
