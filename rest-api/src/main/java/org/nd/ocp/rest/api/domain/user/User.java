package org.nd.ocp.rest.api.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class User {
  private Integer id;
  private String username;
  private String email;
  private String passwordHash;
  private String provider;
  private String role;

  protected User() {}

  void updatePassword(String newPasswordHash) {
    if (newPasswordHash == null || newPasswordHash.trim().isEmpty())
      throw new IllegalArgumentException("Password cannot be empty.");

    this.passwordHash = newPasswordHash;
  }

}
