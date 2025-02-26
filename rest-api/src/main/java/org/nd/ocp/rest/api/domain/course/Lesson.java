package org.nd.ocp.rest.api.domain.course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Lesson {
  private Integer id;
  private String title;
  private String contentUrl;
  private boolean free;
}
