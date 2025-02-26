package org.nd.ocp.rest.api.domain.user;

public interface UserRegistrationService {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  UserOutputDTO registerUser(
      UserInputDTO userInputDTO);
}
