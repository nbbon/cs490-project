package mum.pmp.mstore.service;

import java.util.List;

import mum.pmp.mstore.model.User;


public interface IUserService {
    User getUserById(long id);

    List<User> getAllUsers();

    User save(User user);
}
