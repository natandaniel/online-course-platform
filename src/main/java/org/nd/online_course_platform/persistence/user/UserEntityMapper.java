package org.nd.online_course_platform.persistence.user;

import org.nd.online_course_platform.domain.user.Admin;
import org.nd.online_course_platform.domain.user.Student;
import org.nd.online_course_platform.domain.user.User;

class UserEntityMapper {

  static UserEntity toEntity(User user) {
    if (user == null) throw new IllegalArgumentException("user cannot be null");

    if ("student".equals(user.getRole()))
      return new StudentEntity(user.getId(), user.getUsername(), user.getEmail(),
          user.getPasswordHash(), user.getProvider());

    return new AdminEntity(user.getId(), user.getUsername(), user.getEmail(),
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
