package org.nd.online_course_platform.persistence.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
abstract class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(unique = true, nullable = false)
  private String username;
  @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false)
  private String passwordHash;
  private String role;

  protected UserEntity() {}

}
