package org.nd.ocp.rest.api.domain.course;

import java.util.ArrayList;
import java.util.List;

public record CourseDTO(
    Integer id,
    String title,
    String description,
    String category,
    boolean published,
    List<CourseModuleDTO> modules) {

  static CourseDTO toDTO(Course course) {
    CourseDTO courseDTO = new CourseDTO(course.getId(), course.getTitle(), course.getDescription(),
        course.getCategory(), course.isPublished(), new ArrayList<>());

    courseDTO.modules()
             .addAll(
                 course.getCourseModules()
                       .stream()
                       .map(courseModule -> {

                         CourseModuleDTO
                             courseModuleDTO =
                             new CourseModuleDTO(
                             courseModule.getId(),
                             courseModule.getTitle(),
                             courseModule.getDescription(),
                             new ArrayList<>());

                         courseModuleDTO.lessons()
                                        .addAll(courseModule.getLessons()
                                                            .stream()
                                                            .map(
                                                                lesson -> new LessonDTO(
                                                                lesson.getId(),
                                                                lesson.getTitle(),
                                                                lesson.getContentUrl(),
                                                                lesson.isFree()))
                                                            .toList());

                         return courseModuleDTO;
                       }).toList());

    return courseDTO;
  }

}
