package org.nd.ocp.rest.api.adapters.database.user;

import org.nd.ocp.rest.api.domain.user.Admin;
import org.nd.ocp.rest.api.domain.user.Student;
import org.nd.ocp.rest.api.domain.user.User;

class UserEntityMapper {

  static UserEntity toEntity(User user) {
    if (user == null) throw new IllegalArgumentException("user cannot be null");

    if ("student".equals(user.getRole()))
      return new StudentEntity(user.getId(),
          user.getUsername(), user.getEmail(),
          user.getPasswordHash(), user.getProvider());

    return new AdminEntity(user.getId(),
        user.getUsername(), user.getEmail(),
        user.getPasswordHash(), user.getProvider());
  }

  static User toDomain(UserEntity userEntity) {
    if (userEntity == null) throw new IllegalArgumentException("user entity cannot be null");

    if ("student".equals(userEntity.getRole()))
      return new Student(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(),
          userEntity.getPasswordHash(), userEntity.getProvider());

    return new Admin(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(),
        userEntity.getPasswordHash());

  }

}
