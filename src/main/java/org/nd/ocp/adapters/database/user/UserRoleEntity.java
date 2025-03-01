package org.nd.ocp.adapters.database.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String role;
  private @ManyToOne UserEntity user;
}