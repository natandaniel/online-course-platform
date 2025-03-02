package org.nd.ocp.domain.course;

import java.util.List;

public record CourseModuleDTO(
    Integer id,
    String title,
    String description,
    List<LessonDTO> lessons) {
}
