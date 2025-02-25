package org.nd.online_course_platform.persistence.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("student")
@Data
@EqualsAndHashCode(callSuper = true)
class StudentEntity extends UserEntity {

  StudentEntity(Integer id, String username, String email, String passwordHash) {
    super(id, username, email, passwordHash, "student");
  }
}
