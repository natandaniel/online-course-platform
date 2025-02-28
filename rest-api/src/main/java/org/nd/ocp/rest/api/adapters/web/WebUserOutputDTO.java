package org.nd.ocp.rest.api.adapters.web;

import java.time.LocalDateTime;

public record WebUserOutputDTO(
    Integer id,
    String username,
    String email,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {
}
