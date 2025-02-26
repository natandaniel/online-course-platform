package org.nd.ocp.rest.api.domain.user;

public record UserInputDTO(
    Integer id,
    String username,
    String email,
    String password,
    String provider,
    String role) {}
