package org.nd.ocp.rest.api.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  User save(User user);

  Optional<User> findById(int id);

  Optional<User> findByUsernameOrEmail(String usernameOrEmail);

  List<User> findAll();

  void deleteById(int id);

}
