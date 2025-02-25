package org.nd.online_course_platform.domain.course;

public interface CourseManagementService {

  CourseDTO createCourse(CourseDTO courseDTO);

  void deleteCourse(int courseId);

}
