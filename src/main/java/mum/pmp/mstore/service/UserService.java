package mum.pmp.mstore.service;

import mum.pmp.mstore.model.User;

public interface UserService {
	void save(User user);

    User findByUsername(String username);

}
