package org.nd.ocp.rest.api.adapters.database.course;

import org.nd.ocp.rest.api.domain.course.Lesson;

class LessonEntityMapper {

  static LessonEntity toEntity(
      Lesson lesson) {
    if (lesson == null) throw new IllegalArgumentException("lesson cannot be null");

    LessonEntity
        entity = new LessonEntity();
    entity.setId(lesson.getId());
    entity.setTitle(lesson.getTitle());
    entity.setContent(lesson.getContentUrl());
    entity.setFree(lesson.isFree());
    return entity;
  }

  static Lesson toDomain(
      LessonEntity entity) {
    if (entity == null) throw new IllegalArgumentException("lesson entity cannot be null");

    return new Lesson(entity.getId(), entity.getTitle(), entity.getContent(), entity.isFree());
  }
}