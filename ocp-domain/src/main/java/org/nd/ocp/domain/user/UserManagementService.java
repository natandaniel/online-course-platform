package org.nd.ocp.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementService {
  @Autowired
  UserRepository userRepository;

  public Optional<UserOutputDTO> findById(int userId) {
    return userRepository.findById(userId).map(User::toOutputDTO);
  }

  public void deleteUser(int userId) {
    userRepository.deleteById(userId);
  }
}
