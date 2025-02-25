package org.nd.online_course_platform.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

  public Student(Integer id, String username, String email, String passwordHash) {
    super(id, username, email, passwordHash, "student");
  }
}
