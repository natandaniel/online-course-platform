package org.nd.ocp.rest.api.adapters.web;

import org.nd.ocp.rest.api.domain.course.CourseBrowsingService;
import org.nd.ocp.rest.api.domain.course.CourseDTO;
import org.nd.ocp.rest.api.domain.course.CourseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
  @Autowired
  private CourseBrowsingService courseBrowsingService;
  @Autowired
  private CourseManagementService courseManagementService;

  @GetMapping
  public ResponseEntity<List<EntityModel<CourseDTO>>> getAllCourses() {
    List<EntityModel<CourseDTO>> response =
        courseBrowsingService.findAll()
                             .stream()
                             .map(courseDTO -> {
                               EntityModel<CourseDTO> model = EntityModel.of(courseDTO);
                               model.add(
                                   WebMvcLinkBuilder.linkTo(
                                                        WebMvcLinkBuilder.methodOn(CourseController.class)
                                                                         .getCourseById(courseDTO.id()))
                                                    .withSelfRel());
                               return model;
                             })
                             .toList();

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{courseId}")
  public ResponseEntity<EntityModel<CourseDTO>> getCourseById(@PathVariable int courseId) {
    Optional<CourseDTO> optCourseDTO = courseBrowsingService.findById(courseId);

    if (optCourseDTO.isEmpty()) return ResponseEntity.notFound().build();

    EntityModel<CourseDTO> model = EntityModel.of(optCourseDTO.get());
    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class)
                                                        .getCourseById(courseId))
                               .withSelfRel());
    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class)
                                                        .getAllCourses())
                               .withRel("courses"));
    return ResponseEntity.ok(model);
  }

  @GetMapping("/{category}")
  public ResponseEntity<List<EntityModel<CourseDTO>>> getCoursesByCategory(
      @PathVariable String category) {
    List<EntityModel<CourseDTO>> response =
        courseBrowsingService.findByCategory(category)
                             .stream()
                             .map(courseDTO -> {
                               EntityModel<CourseDTO> model = EntityModel.of(courseDTO);
                               model.add(
                                   WebMvcLinkBuilder.linkTo(
                                                        WebMvcLinkBuilder.methodOn(CourseController.class)
                                                                         .getCourseById(courseDTO.id()))
                                                    .withSelfRel());
                               return model;
                             })
                             .toList();

    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<EntityModel<CourseDTO>> createCourse(@RequestBody CourseDTO courseDTO) {
    CourseDTO createdCourseDTO = courseManagementService.createCourse(courseDTO);

    EntityModel<CourseDTO> model = EntityModel.of(createdCourseDTO);
    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class)
                                                        .getCourseById(createdCourseDTO.id()))
                               .withSelfRel());

    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class)
                                                        .getAllCourses())
                               .withRel("courses"));

    return ResponseEntity.created(
                             WebMvcLinkBuilder.linkTo(
                                                  WebMvcLinkBuilder.methodOn(CourseController.class)
                                                                   .getCourseById(createdCourseDTO.id()))
                                              .toUri())
                         .body(model);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{courseId}")
  public ResponseEntity<Void> deleteCourseById(@PathVariable int courseId) {
    courseManagementService.deleteCourse(courseId);

    return ResponseEntity.noContent().build();
  }

}
