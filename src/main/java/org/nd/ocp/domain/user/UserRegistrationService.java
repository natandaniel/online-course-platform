package org.nd.ocp.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistrationService {
  @Autowired
  private UserRepository userRepository;

  public UserOutputDTO registerUser(UserInputDTO userInputDTO) {
    if (findByUsernameOrEmail(userInputDTO.username()).isPresent())
      throw new IllegalArgumentException("Username already taken.");

    if (findByUsernameOrEmail(userInputDTO.email()).isPresent())
      throw new IllegalArgumentException("Email already in use.");

    return userRepository.save(User.fromInputDTO(userInputDTO)).toOutputDTO();
  }

  public Optional<UserOutputDTO> findByUsernameOrEmail(String usernameOrEmail) {
    return userRepository.findByUsernameOrEmail(usernameOrEmail)
                         .map(User::toOutputDTO);
  }

}
