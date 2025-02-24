package org.nd.online_course_platform.persistence;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

interface SpringDataCourseRepository extends ListCrudRepository<CourseEntity, Integer> {

  Optional<CourseEntity> findByTitle(String title);

  List<CourseEntity> findByCategory(String category);

}
