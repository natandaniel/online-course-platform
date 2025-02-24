package org.nd.online_course_platform.domain.course;

public record LessonDTO(
    Integer id,
    String title,
    String contentUrl,
    boolean free) {
}
