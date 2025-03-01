package org.nd.ocp.domain.user;

import java.util.List;

public record UserInputDTO(
    String username,
    String email,
    String password,
    String provider,
    List<String> roles) {}
