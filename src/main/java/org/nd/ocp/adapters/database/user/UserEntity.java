package org.nd.ocp.adapters.database.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.nd.ocp.domain.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
class UserEntity {
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch =
      FetchType.EAGER)
  private final List<UserRoleEntity> roles = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(unique = true)
  private String username;
  @Column(unique = true)
  private String email;
  private String password;
  private String provider;
  private boolean accountExpired;
  private boolean accountLocked;
  private boolean credentialsExpired;
  private boolean enabled;
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;
  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime updatedAt;

  public UserEntity(String username, String email, String password, String provider,
      List<Pair<Integer, String>> roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.provider = provider;
    this.roles.addAll(roles.stream()
                           .map(pair -> new UserRoleEntity(pair.getLeft(), pair.getRight(), this))
                           .toList());
  }

  static UserEntity toEntity(User user) {
    if (user == null) throw new IllegalArgumentException("user cannot be null");

    UserEntity userEntity = new UserEntity(user.getUsername(), user.getEmail(),
        user.getPassword(), user.getProvider(), user.getRoles());
    userEntity.setId(user.getId());
    userEntity.setAccountExpired(user.isAccountExpired());
    userEntity.setAccountLocked(user.isAccountLocked());
    userEntity.setCredentialsExpired(user.isCredentialsExpired());
    userEntity.setEnabled(user.isEnabled());

    return userEntity;
  }

  static User toDomain(UserEntity userEntity) {
    if (userEntity == null) throw new IllegalArgumentException("user entity cannot be null");

    User user = new User(userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword(),
        userEntity.getProvider(),
        userEntity.getRoles()
                  .stream()
                  .map(UserRoleEntity::getRole)
                  .toList());

    user.setId(userEntity.getId());
    user.setAccountExpired(userEntity.isAccountExpired());
    user.setAccountLocked(userEntity.isAccountLocked());
    user.setCredentialsExpired(userEntity.isCredentialsExpired());
    user.setEnabled(userEntity.isEnabled());
    user.setCreatedAt(userEntity.getCreatedAt());
    user.setUpdatedAt(userEntity.getUpdatedAt());

    return user;
  }

}
