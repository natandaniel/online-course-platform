package org.nd.online_course_platform.domain.course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
  Course save(Course course);

  Optional<Course> findByTitle(String title);

  List<Course> findAll();

  List<Course> findByCategory(String category);

  long count();

  void delete(Course course);
}
