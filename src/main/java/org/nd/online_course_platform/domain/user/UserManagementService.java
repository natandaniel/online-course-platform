package org.nd.online_course_platform.domain.user;

import java.util.Optional;

public interface UserManagementService {

  Optional<UserOutputDTO> findById(int userId);

  void deleteUser(int userId);

}
