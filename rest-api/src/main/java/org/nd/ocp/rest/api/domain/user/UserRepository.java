package org.nd.ocp.rest.api.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  User save(
      User user);

  Optional<User> findById(int id);

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  List<User> findAll();

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  void deleteById(int id);

}
