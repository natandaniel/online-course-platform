package org.nd.ocp.rest.api.adapters.database.course;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modules")
@Data
class CourseModuleEntity {
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
  private final List<LessonEntity> lessons = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String title;
  private String description;
  @ManyToOne
  private CourseEntity course;
}
