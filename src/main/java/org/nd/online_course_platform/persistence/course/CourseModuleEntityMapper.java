package org.nd.online_course_platform.persistence.course;

import org.nd.online_course_platform.domain.course.CourseModule;

import java.util.List;

class CourseModuleEntityMapper {

  static CourseModule toDomain(CourseModuleEntity entity) {
    if (entity == null) throw new IllegalArgumentException("course module entity cannot be null");

    CourseModule courseModule =
        new CourseModule(entity.getId(), entity.getTitle(), entity.getDescription());
    courseModule.getLessons()
                .addAll(entity.getLessons().stream().map(LessonEntityMapper::toDomain).toList());
    return courseModule;
  }

  static CourseModuleEntity toEntity(CourseModule courseModule) {
    if (courseModule == null) throw new IllegalArgumentException("course module cannot be null");

    CourseModuleEntity entity = new CourseModuleEntity();
    entity.setId(courseModule.getId());
    entity.setTitle(courseModule.getTitle());
    entity.setDescription(courseModule.getDescription());

    List<LessonEntity> lessonEntities = courseModule.getLessons()
                                                    .stream()
                                                    .map(LessonEntityMapper::toEntity)
                                                    .toList();

    lessonEntities.forEach(lessonEntity -> lessonEntity.setModule(entity));

    entity.getLessons().addAll(lessonEntities);

    return entity;
  }
}