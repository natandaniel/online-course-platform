package org.nd.online_course_platform.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementServiceImpl implements UserManagementService {
  @Autowired
  UserRepository userRepository;

  @Override
  public Optional<UserOutputDTO> findById(int userId) {
    return userRepository.findById(userId).map(user -> new UserOutputDTO(user.getId(),
        user.getUsername(), user.getEmail()));
  }

  @Override
  public void deleteUser(int userId) {
    userRepository.deleteById(userId);
  }
}
