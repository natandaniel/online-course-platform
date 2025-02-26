package org.nd.ocp.rest.api.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

  public Student(Integer id, String username, String email, String passwordHash, String provider) {
    super(id, username, email, passwordHash, "student", provider);
  }
}
