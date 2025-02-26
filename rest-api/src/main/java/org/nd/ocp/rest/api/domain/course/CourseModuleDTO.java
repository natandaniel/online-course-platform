package org.nd.ocp.rest.api.domain.course;

import java.util.List;

public record CourseModuleDTO(
    Integer id,
    String title,
    String description,
    List<LessonDTO> lessons) {
}
