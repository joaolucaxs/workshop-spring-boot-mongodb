package com.joaolucas.workshopmongodb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaolucas.workshopmongodb.entities.Post;
import com.joaolucas.workshopmongodb.entities.User;
import com.joaolucas.workshopmongodb.repositories.PostRepository;
import com.joaolucas.workshopmongodb.repositories.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com");
		User u2 = new User(null, "Alex Green", "alex@gmail.com");
		User u3 = new User(null, "Bob Gray", "bob@gmail.com");
		
		Post p1 = new Post(null, Instant.parse("2019-06-20T19:53:07Z"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", u1);
		Post p2 = new Post(null, Instant.parse("2019-07-21T03:42:10Z"), "Bom dia", "Acordei feliz hoje!", u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		postRepository.saveAll(Arrays.asList(p1,p2));

		
	}

}
