package org.nd.online_course_platform.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
  @Autowired
  private CourseRepository courseRepository;

  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  public Course createCourse(String title, String description, String category) {
    return new Course(title, description, category, false);
  }

  public void createCourseModule(Course course, String moduleTitle, String moduleDescription) {
    course.createModule(moduleTitle, moduleDescription);
  }

  public void removeCourseModule(Course course, String moduleTitle) {
    course.removeModule(moduleTitle);
  }

  public void swapCourseModules(Course course, String moduleTitle1, String moduleTitle2) {
    course.swapModules(moduleTitle1, moduleTitle2);
  }

  public void addCourseLesson(Course course, String moduleTitle, String lessonTitle, String content,
      boolean free) {
    course.addLesson(moduleTitle, lessonTitle, content, free);
  }

  public void removeLesson(Course course, String moduleTitle, String lessonTitle) {
    course.removeLesson(moduleTitle, lessonTitle);
  }

  public void swapLessons(Course course, String moduleTitle, String lessonTitle1,
      String lessonTitle2) {
    course.swapLessons(moduleTitle, lessonTitle1, lessonTitle2);
  }

  public void updateLessonTitle(Course course, String moduleTitle, String oldTitle,
      String newTitle) {
    course.updateLessonTitle(moduleTitle, oldTitle, newTitle);
  }

  public void updateLessonContent(Course course, String moduleTitle, String lessonTitle,
      String newContent) {
    course.updateLessonContent(moduleTitle, lessonTitle, newContent);
  }

  public void updateLessonStatus(Course course, String moduleTitle, String lessonTitle,
      boolean free) {
    course.updateLessonStatus(moduleTitle, lessonTitle, false);
  }

  public void publishCourse(Course course) {
    course.publish();
  }

}
