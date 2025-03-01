package org.nd.ocp.adapters.database.course;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

interface SpringDataCourseRepository extends
    ListCrudRepository<CourseEntity,
        Integer> {

  Optional<CourseEntity> findByTitle(
      String title);

  List<CourseEntity> findByCategory(
      String category);

}
