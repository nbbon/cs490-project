package mum.pmp.mstore.service.security;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mum.pmp.mstore.repository.profile.UserRepository;
import mum.pmp.mstore.service.IUserService;
import mum.pmp.mstore.model.User;

@Service
@Transactional
public class UserService implements  IUserService{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public void delete(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserById(String userId) {
		return userRepository.getOne(Long.parseLong(userId));
	}

   
}
