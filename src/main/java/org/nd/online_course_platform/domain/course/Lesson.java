package org.nd.online_course_platform.domain.course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Lesson {
  private String title;
  private String content;
  private boolean free;
}
