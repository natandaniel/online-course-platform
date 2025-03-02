package org.nd.ocp.database.course;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
class CourseEntity {
  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<CourseModuleEntity> modules = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String title;
  private String description;
  private String category;
  private boolean published = false;
}
