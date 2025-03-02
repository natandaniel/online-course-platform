package org.nd.ocp.authorization.server;

import java.time.LocalDateTime;

public record WebUserOutputDTO(
    Integer id,
    String username,
    String email,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {
}
