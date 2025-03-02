package org.nd.ocp.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseBrowsingService {
  @Autowired
  private CourseRepository courseRepository;

  public List<CourseDTO> findAll() {
    return List.of();
  }

  public List<CourseDTO> findByCategory(String category) {
    return List.of();
  }

  public Optional<CourseDTO> findById(int id) {
    return Optional.empty();
  }
}
