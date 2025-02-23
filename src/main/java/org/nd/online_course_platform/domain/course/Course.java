package org.nd.online_course_platform.domain.course;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class Course {
  private final List<Module> modules = new ArrayList<>();
  private String title;
  private String description;
  private String category;
  private boolean published;

  void createModule(String moduleTitle, String moduleDescription) {
    if (getModuleByTitle(moduleTitle).isEmpty())
      modules.add(new Module(moduleTitle, moduleDescription));
  }

  void removeModule(String moduleTitle) {
    modules.removeIf(module -> module.getTitle().equalsIgnoreCase(moduleTitle));
  }

  void swapModules(String moduleTitle1, String moduleTitle2) {
    Optional<Module> optModule1 = getModuleByTitle(moduleTitle1);
    Optional<Module> optModule2 = getModuleByTitle(moduleTitle2);

    if (optModule1.isEmpty() || optModule2.isEmpty()) return;

    Module module1 = optModule1.get();
    Module module2 = optModule2.get();

    int module1Index = modules.indexOf(module1);
    int module2Index = modules.indexOf(module2);

    modules.set(module1Index, module2);
    modules.set(module2Index, module1);
  }

  void addLesson(String moduleTitle, String lessonTitle, String content, boolean free) {
    getModuleByTitle(moduleTitle).ifPresent(module -> module.addLesson(lessonTitle, content, free));
  }

  void removeLesson(String moduleTitle, String lessonTitle) {
    getModuleByTitle(moduleTitle).ifPresent(module -> module.removeLesson(lessonTitle));
  }

  void swapLessons(String moduleTitle, String lessonTitle1, String lessonTitle2) {
    getModuleByTitle(moduleTitle).ifPresent(
        module -> module.swapLessons(lessonTitle1, lessonTitle2));
  }

  void updateLessonTitle(String moduleTitle, String oldTitle, String newTitle) {
    getModuleByTitle(moduleTitle).ifPresent(module -> module.updateLessonTitle(oldTitle, newTitle));
  }

  void updateLessonContent(String moduleTitle, String lessonTitle, String newContent) {
    getModuleByTitle(moduleTitle).ifPresent(
        module -> module.updateLessonContent(lessonTitle, newContent));
  }

  void updateLessonStatus(String moduleTitle, String lessonTitle, boolean free) {
    getModuleByTitle(moduleTitle).ifPresent(module -> module.updateLessonStatus(lessonTitle, free));
  }

  private Optional<Module> getModuleByTitle(String moduleTitle) {
    return modules.stream()
                  .filter(module -> module.getTitle().equalsIgnoreCase(moduleTitle))
                  .findAny();
  }

}
