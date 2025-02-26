package org.nd.ocp.rest.api.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements
    UserRegistrationService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public UserOutputDTO registerUser(
      UserInputDTO userInputDTO) {

    if ("student".equals(userInputDTO.role())) {
      User student =
          userRepository.save(
              new Student(null, userInputDTO.username(),
                  userInputDTO.email(),
              userInputDTO.password(), userInputDTO.provider()));

      return new UserOutputDTO(student.getId(),
          student.getUsername(), student.getEmail());
    }

    User admin =
        userRepository.save(
            new Admin(null, userInputDTO.username(),
                userInputDTO.email(),
            userInputDTO.password()));

    return new UserOutputDTO(admin.getId(),
        admin.getUsername(), admin.getEmail());
  }

}
