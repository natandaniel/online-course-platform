package org.nd.online_course_platform.domain.course;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class CourseModule {
  private final List<Lesson> lessons = new ArrayList<>();
  private Integer id;
  private String title;
  private String description;

  void addLesson(String title, String content, boolean free) {
    if (getLessonByTitle(title).isEmpty()) lessons.add(new Lesson(null, title, content, free));
  }

  void removeLesson(String title) {
    lessons.removeIf(lesson -> lesson.getTitle().equalsIgnoreCase(title));
  }

  void swapLessons(String lessonTitle1, String lessonTitle2) {
    Optional<Lesson> optLesson1 = getLessonByTitle(lessonTitle1);
    Optional<Lesson> optLesson2 = getLessonByTitle(lessonTitle2);

    if (optLesson1.isEmpty() || optLesson2.isEmpty()) return;

    Lesson lesson1 = optLesson1.get();
    Lesson lesson2 = optLesson2.get();

    int lesson1Index = lessons.indexOf(lesson1);
    int lesson2Index = lessons.indexOf(lesson2);

    lessons.set(lesson1Index, lesson2);
    lessons.set(lesson2Index, lesson1);
  }

  void updateLessonTitle(String oldTitle, String newTitle) {
    getLessonByTitle(oldTitle).ifPresent(lesson -> lesson.setTitle(newTitle));
  }

  void updateLessonContent(String lessonTitle, String newContent) {
    getLessonByTitle(lessonTitle).ifPresent(lesson -> lesson.setContentUrl(newContent));
  }

  void updateLessonStatus(String lessonTitle, boolean free) {
    getLessonByTitle(lessonTitle).ifPresent(lesson -> lesson.setFree(free));
  }

  private Optional<Lesson> getLessonByTitle(String lessonTitle) {
    return lessons.stream()
                  .filter(lesson -> lesson.getTitle().equalsIgnoreCase(lessonTitle))
                  .findAny();
  }

}
