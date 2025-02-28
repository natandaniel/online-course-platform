package org.nd.ocp.rest.api.domain.user;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
  private Integer id;
  private String username;
  private String email;
  private final List<Pair<Integer, String>> roles = new ArrayList<>();
  private String provider;
  private String password;
  private boolean accountExpired;
  private boolean accountLocked;
  private boolean credentialsExpired;
  private boolean enabled;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public User(String username, String email, String password, String provider, List<String> roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.provider = provider;
    for (String role : roles)
      this.roles.add(Pair.of(null, role));
    enabled = true;
  }

  static User fromInputDTO(UserInputDTO userInputDTO) {
    return new User(userInputDTO.username(), userInputDTO.email(), userInputDTO.password(),
        userInputDTO.provider(), userInputDTO.roles());
  }

  UserOutputDTO toOutputDTO() {
    return new UserOutputDTO(id, username, email, password,
        roles.stream().map(Pair::getRight).toList(), accountExpired, accountLocked,
        credentialsExpired, enabled, createdAt, updatedAt);
  }

}
