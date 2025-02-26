package org.nd.online_course_platform.domain.user;

public record UserInputDTO(
    Integer id,
    String username,
    String email,
    String password,
    String provider,
    String role) {}
