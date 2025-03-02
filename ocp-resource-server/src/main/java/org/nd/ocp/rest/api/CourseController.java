package org.nd.ocp.rest.api;

import org.apache.commons.lang3.StringUtils;
import org.nd.ocp.domain.course.CourseBrowsingService;
import org.nd.ocp.domain.course.CourseDTO;
import org.nd.ocp.domain.course.CourseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
  @Autowired
  private CourseBrowsingService courseBrowsingService;
  @Autowired
  private CourseManagementService courseManagementService;

  @GetMapping
  public ResponseEntity<List<EntityModel<CourseDTO>>> getCourses(
      @RequestParam(required = false) String category) {
    List<CourseDTO> courses = StringUtils.isNotBlank(category) ?
        courseBrowsingService.findByCategory(category) : courseBrowsingService.findAll();

    List<EntityModel<CourseDTO>> response =
        courses.stream()
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
    EntityModel<CourseDTO> model =
        EntityModel.of(courseBrowsingService.findById(courseId)
                                            .orElseThrow(() -> new ResourceNotFoundException(
                                                "no course found with id:" + courseId)));

    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class)
                                                        .getCourseById(courseId))
                               .withSelfRel());
    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CourseController.class)
                                                        .getCourses(""))
                               .withRel("courses"));

    return ResponseEntity.ok(model);
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
                                                        .getCourses(""))
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
