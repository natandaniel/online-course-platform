package org.nd.ocp.database.user;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
interface SpringDataUserRepository extends ListCrudRepository<UserEntity, Integer> {
  
  Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
