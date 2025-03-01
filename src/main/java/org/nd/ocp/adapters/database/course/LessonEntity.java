package org.nd.ocp.adapters.database.course;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lessons")
@Data
class LessonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String title;
  @Column(columnDefinition = "TEXT")
  private String content;
  private boolean free;
  @ManyToOne
  private CourseModuleEntity module;
}
