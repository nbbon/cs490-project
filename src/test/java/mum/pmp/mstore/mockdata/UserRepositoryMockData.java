package mum.pmp.mstore.mockdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.repository.profile.UserRepository;

@Component
public class UserRepositoryMockData //implements UserRepository
{

	List<User> users = new ArrayList<>();
	
	//@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public List<User> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public List<User> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public <S extends User> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public <S extends User> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public void deleteInBatch(Iterable<User> entities) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public User getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public <S extends User> S save(S entity) {
		// TODO Auto-generated method stub
		users.add(entity);
		return entity;
	}

	//@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	//@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	//@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void deleteAll(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public <S extends User> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	//@Override
	public <S extends User> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	//@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		//profiles
		User user = new User();
		user.setUsername("stanley.julien20@gmail.com");
		//profile.setToken("5040");
		return user;
	}

}
