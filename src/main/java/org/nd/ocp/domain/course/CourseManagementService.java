package org.nd.ocp.domain.course;

public interface CourseManagementService {

  CourseDTO createCourse(
      CourseDTO courseDTO);

  void deleteCourse(int courseId);

}
