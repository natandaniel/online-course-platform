package org.nd.online_course_platform.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseBrowsingServiceImpl implements CourseBrowsingService {
  @Autowired
  private CourseRepository courseRepository;

  @Override
  public List<CourseDTO> findAll() {
    return List.of();
  }

  @Override
  public List<CourseDTO> findByCategory(String category) {
    return List.of();
  }

  @Override
  public Optional<CourseDTO> findById(int id) {
    return Optional.empty();
  }
}
