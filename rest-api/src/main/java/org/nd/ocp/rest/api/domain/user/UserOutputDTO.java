package org.nd.ocp.rest.api.domain.user;

public record UserOutputDTO(
    Integer id,
    String username,
    String email) {}
