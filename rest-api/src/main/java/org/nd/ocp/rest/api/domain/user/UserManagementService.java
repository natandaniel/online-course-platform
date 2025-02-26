package org.nd.ocp.rest.api.domain.user;

import java.util.Optional;

public interface UserManagementService {

  Optional<UserOutputDTO> findById(int userId);

  void deleteUser(int userId);

}
