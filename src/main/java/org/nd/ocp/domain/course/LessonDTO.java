package org.nd.ocp.domain.course;

public record LessonDTO(
    Integer id,
    String title,
    String contentUrl,
    boolean free) {
}
