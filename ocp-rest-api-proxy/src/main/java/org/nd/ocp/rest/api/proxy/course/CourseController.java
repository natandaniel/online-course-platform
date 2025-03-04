package org.nd.ocp.rest.api.proxy.course;

import org.nd.ocp.domain.course.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

  @Value("${restapi.courses-url}")
  private String baseUrl;

  @Autowired
  private RestClient restClient;

  @GetMapping
  public ResponseEntity<List<EntityModel<CourseDTO>>> getCourses(
      @RequestParam(required = false) String category) {
    String url = category == null ? baseUrl : baseUrl + "?category=" + category;

    List<EntityModel<CourseDTO>> response =
        restClient.get()
                  .uri(url)
                  .attributes(clientRegistrationId("online-course-platform"))
                  .retrieve()
                  .body(new ParameterizedTypeReference<>() {});

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{courseId}")
  public ResponseEntity<EntityModel<CourseDTO>> getCourseById(@PathVariable int courseId) {
    String url = baseUrl + "/" + courseId;

    EntityModel<CourseDTO> response =
        restClient.get().uri(url).retrieve().body(new ParameterizedTypeReference<>() {});

    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<EntityModel<CourseDTO>> createCourse(@RequestBody CourseDTO courseDTO) {
    EntityModel<CourseDTO> response = restClient.post()
                                                .uri(baseUrl)
                                                .body(courseDTO)
                                                .retrieve()
                                                .body(new ParameterizedTypeReference<>() {});

    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{courseId}")
  public ResponseEntity<Void> deleteCourseById(@PathVariable int courseId) {
    String url = baseUrl + "/" + courseId;

    restClient.delete().uri(url).retrieve().toBodilessEntity();

    return ResponseEntity.noContent().build();
  }
}