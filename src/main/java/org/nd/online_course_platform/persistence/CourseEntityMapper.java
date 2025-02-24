package org.nd.online_course_platform.persistence;

import org.nd.online_course_platform.domain.course.Course;

class CourseEntityMapper {

  static CourseEntity toEntity(Course course) {
    if (course == null) throw new IllegalArgumentException("course cannot be null");

    CourseEntity entity = new CourseEntity();
    entity.setId(course.getId());
    entity.setTitle(course.getTitle());
    entity.setDescription(course.getDescription());
    entity.setCategory(course.getCategory());
    entity.setPublished(course.isPublished());
    entity.getModules()
          .addAll(
              course.getCourseModules().stream().map(CourseModuleEntityMapper::toEntity).toList());

    return entity;
  }

  static Course toDomain(CourseEntity entity) {
    if (entity == null) throw new IllegalArgumentException("course entity cannot be null");

    Course course = new Course(
        entity.getId(),
        entity.getTitle(),
        entity.getDescription(),
        entity.getCategory(),
        entity.isPublished());
    course.getCourseModules()
          .addAll(entity.getModules().stream().map(CourseModuleEntityMapper::toDomain).toList());

    return course;
  }
}