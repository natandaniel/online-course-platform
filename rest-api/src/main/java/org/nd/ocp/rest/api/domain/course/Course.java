package org.nd.ocp.rest.api.domain.course;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class Course {
  private final List<CourseModule> courseModules =
      new ArrayList<>();
  private Integer id;
  private String title;
  private String description;
  private String category;
  private boolean published;

  Optional<CourseModule> createModule(
      String moduleTitle, String moduleDescription) {
    if (getModuleByTitle(moduleTitle).isEmpty()) {
      CourseModule
          courseModule =
          new CourseModule(null, moduleTitle,
              moduleDescription);
      courseModules.add(courseModule);
      return Optional.of(courseModule);
    }

    return Optional.empty();
  }

  void removeModule(String moduleTitle) {
    courseModules.removeIf(courseModule -> courseModule.getTitle().equalsIgnoreCase(moduleTitle));
  }

  void swapModules(String moduleTitle1, String moduleTitle2) {
    Optional<CourseModule> optModule1 =
        getModuleByTitle(moduleTitle1);
    Optional<CourseModule> optModule2 =
        getModuleByTitle(moduleTitle2);

    if (optModule1.isEmpty() || optModule2.isEmpty()) return;

    CourseModule courseModule1 = optModule1.get();
    CourseModule courseModule2 = optModule2.get();

    int module1Index = courseModules.indexOf(courseModule1);
    int module2Index = courseModules.indexOf(courseModule2);

    courseModules.set(module1Index, courseModule2);
    courseModules.set(module2Index, courseModule1);
  }

  void publish() {
    if (courseModules.stream().allMatch(courseModule -> courseModule.getLessons().isEmpty()))
      throw new IllegalStateException("cannot publish empty course");

    published = true;
  }

  private Optional<CourseModule> getModuleByTitle(
      String moduleTitle) {
    return courseModules.stream()
                        .filter(
                            courseModule -> courseModule.getTitle().equalsIgnoreCase(moduleTitle))
                        .findAny();
  }

}
