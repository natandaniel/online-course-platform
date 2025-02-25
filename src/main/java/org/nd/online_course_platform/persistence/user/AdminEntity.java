package org.nd.online_course_platform.persistence.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("admin")
@Data
@EqualsAndHashCode(callSuper = true)
class AdminEntity extends UserEntity {

  AdminEntity(Integer id, String username, String email, String passwordHash) {
    super(id, username, email, passwordHash, "admin");
  }
}
