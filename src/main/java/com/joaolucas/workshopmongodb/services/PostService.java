package com.joaolucas.workshopmongodb.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaolucas.workshopmongodb.entities.Post;
import com.joaolucas.workshopmongodb.repositories.PostRepository;
import com.joaolucas.workshopmongodb.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Post> findByTitle(String text) {
		return repository.searchTitle(text);
	}

	public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
		maxDate.plus(1, ChronoUnit.DAYS);
		return repository.fullSearch(text, minDate, maxDate);
	}

}
