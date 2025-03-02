package org.nd.ocp.database.user;

import org.nd.ocp.domain.user.User;
import org.nd.ocp.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {

  @Autowired
  private SpringDataUserRepository springDataUserRepository;

  @Override
  public User save(User user) {
    return UserEntity.toDomain(springDataUserRepository.save(UserEntity.toEntity(user)));
  }

  @Override
  public Optional<User> findById(int id) {
    return springDataUserRepository.findById(id).map(UserEntity::toDomain);
  }

  @Override
  public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
    return springDataUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                                   .map(UserEntity::toDomain);
  }

  @Override
  public List<User> findAll() {
    return springDataUserRepository.findAll().stream().map(UserEntity::toDomain).toList();
  }

  @Override
  public void deleteById(int id) {
    springDataUserRepository.deleteById(id);
  }

}
