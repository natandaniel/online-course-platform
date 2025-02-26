package org.nd.ocp.rest.api.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {

  public Admin(Integer id, String username, String email, String passwordHash) {
    super(id, username, email, passwordHash, "admin", "local");
  }
}
