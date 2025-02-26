package org.nd.ocp.rest.api.domain.course;

public interface CourseManagementService {

  CourseDTO createCourse(
      CourseDTO courseDTO);

  void deleteCourse(int courseId);

}
