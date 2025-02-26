package org.nd.ocp.rest.api.adapters.database.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("student")
@Data
@EqualsAndHashCode(callSuper = true)
class StudentEntity extends UserEntity {

  StudentEntity(Integer id, String username, String email, String passwordHash, String provider) {
    super(id, username, email, passwordHash, provider, "student");
  }
}
