package com.joaolucas.workshopmongodb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaolucas.workshopmongodb.dtos.AuthorDTO;
import com.joaolucas.workshopmongodb.dtos.CommentDTO;
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
		postRepository.deleteAll();

		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com");
		User u2 = new User(null, "Alex Green", "alex@gmail.com");
		User u3 = new User(null, "Bob Gray", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));

		Post p1 = new Post(null, Instant.parse("2019-06-20T19:53:07Z"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(u1));
		Post p2 = new Post(null, Instant.parse("2019-07-21T03:42:10Z"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(u1));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.parse("2019-06-21T12:53:07Z"), new AuthorDTO(u2));
		CommentDTO c2 = new CommentDTO("Aproveite!", Instant.parse("2019-06-28T15:53:07Z"), new AuthorDTO(u3));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Instant.parse("2019-06-21T12:55:07Z"), new AuthorDTO(u2));

		p1.getComments().addAll(Arrays.asList(c1,c2));
		p2.getComments().add(c3);

		postRepository.saveAll(Arrays.asList(p1,p2));
		
		u1.getPosts().addAll(Arrays.asList(p1,p2));
		userRepository.save(u1);
		
		

		
	}

}
