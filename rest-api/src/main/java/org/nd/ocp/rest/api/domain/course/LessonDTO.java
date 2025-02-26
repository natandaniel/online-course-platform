package org.nd.ocp.rest.api.domain.course;

public record LessonDTO(
    Integer id,
    String title,
    String contentUrl,
    boolean free) {
}
