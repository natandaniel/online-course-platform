package org.nd.ocp.rest.api.adapters.database.user;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

interface SpringDataUserRepository extends
    ListCrudRepository<UserEntity, Integer> {

  Optional<UserEntity> findByUsername(
      String username);

  Optional<UserEntity> findByEmail(
      String email);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
