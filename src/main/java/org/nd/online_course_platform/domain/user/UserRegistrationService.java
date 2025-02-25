package org.nd.online_course_platform.domain.user;

public interface UserRegistrationService {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  UserOutputDTO registerUser(UserInputDTO userInputDTO);
}
