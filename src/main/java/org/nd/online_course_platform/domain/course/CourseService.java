package org.nd.online_course_platform.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
  @Autowired
  private CourseRepository courseRepository;

  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  public Optional<Course> findByTitle(String title) {
    return courseRepository.findByTitle(title);
  }

  public List<Course> findByCategory(String category) {
    return courseRepository.findByCategory(category);
  }

  public Course save(Course course) {
    return courseRepository.save(course);
  }

  public Course createCourse(String title, String description, String category) {
    return new Course(title, description, category, false);
  }

  public void updateCourseTitle(Course course, String newTitle) {
    course.setTitle(newTitle);
  }

  public void updateCourseDescription(Course course, String newDescription) {
    course.setDescription(newDescription);
  }

  public void updateCourseCategory(Course course, String newCategory) {
    course.setCategory(newCategory);
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
