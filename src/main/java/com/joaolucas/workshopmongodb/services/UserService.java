package com.joaolucas.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.joaolucas.workshopmongodb.dtos.UserDTO;
import com.joaolucas.workshopmongodb.entities.User;
import com.joaolucas.workshopmongodb.repositories.UserRepository;
import com.joaolucas.workshopmongodb.services.exceptions.DataBaseException;
import com.joaolucas.workshopmongodb.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(String id) {
		try {
			findById(id);
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	/*
	 * public User update(String id, User obj) { try { Optional<User> entity =
	 * repository.findById(id); updateData(entity, obj); return
	 * repository.save(entity); } catch (EntityNotFoundException e) { throw new
	 * ResourceNotFoundException(id); } }
	 * 
	 * private void updateData(Optional<User> entity, User obj) {
	 * entity.setName(obj.getName()); entity.setEmail(obj.getEmail()); }
	 */
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

}
