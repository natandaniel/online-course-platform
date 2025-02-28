package org.nd.ocp.rest.api.domain.user;

import java.time.LocalDateTime;
import java.util.List;

public record UserOutputDTO(
    Integer id,
    String username,
    String email,
    String password,
    List<String> roles,
    boolean accountExpired,
    boolean accountLocked,
    boolean credentialsExpired,
    boolean enabled,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {
  }
