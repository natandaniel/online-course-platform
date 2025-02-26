package org.nd.ocp.rest.api.adapters.database.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("admin")
@Data
@EqualsAndHashCode(callSuper = true)
class AdminEntity extends UserEntity {

  AdminEntity(Integer id, String username, String email, String passwordHash, String provider) {
    super(id, username, email, passwordHash, provider, "admin");
  }
}
