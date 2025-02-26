package org.nd.ocp.rest.api.adapters.database.user;

import org.nd.ocp.rest.api.domain.user.User;
import org.nd.ocp.rest.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {
  @Autowired
  private SpringDataUserRepository
      springDataUserRepository;

  @Override
  public User save(User user) {
    return UserEntityMapper.toDomain(
        springDataUserRepository.save(
            UserEntityMapper.toEntity(user)));
  }

  @Override
  public Optional<User> findById(int id) {
    return springDataUserRepository.findById(id).map(
        UserEntityMapper::toDomain);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return springDataUserRepository.findByUsername(username).map(
        UserEntityMapper::toDomain);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return springDataUserRepository.findByEmail(email).map(
        UserEntityMapper::toDomain);
  }

  @Override
  public List<User> findAll() {
    return springDataUserRepository.findAll().stream().map(
        UserEntityMapper::toDomain).toList();
  }

  @Override
  public boolean existsByUsername(String username) {
    return springDataUserRepository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return springDataUserRepository.existsByEmail(email);
  }

  @Override
  public void deleteById(int id) {
    springDataUserRepository.deleteById(id);
  }

}
