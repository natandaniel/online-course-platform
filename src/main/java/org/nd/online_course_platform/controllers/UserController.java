package org.nd.online_course_platform.controllers;

import jakarta.validation.Valid;
import org.nd.online_course_platform.domain.user.UserInputDTO;
import org.nd.online_course_platform.domain.user.UserManagementService;
import org.nd.online_course_platform.domain.user.UserOutputDTO;
import org.nd.online_course_platform.domain.user.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  @Autowired
  private UserManagementService userManagementService;
  @Autowired
  private UserRegistrationService userRegistrationService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{userId}")
  public ResponseEntity<EntityModel<UserOutputDTO>> getUserById(@PathVariable int userId) {
    Optional<UserOutputDTO> optUserOutputDTO = userManagementService.findById(userId);

    if (optUserOutputDTO.isEmpty()) return ResponseEntity.notFound().build();

    EntityModel<UserOutputDTO> model = EntityModel.of(optUserOutputDTO.get());
    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                                                        .getUserById(userId))
                               .withSelfRel());

    return ResponseEntity.ok(model);
  }

  @PreAuthorize("hasRole('STUDENT')")
  @PostMapping("/register")
  public ResponseEntity<String> registerStudent(
      @Valid @RequestBody UserInputDTO userInputDTO) {
    if (userRegistrationService.existsByUsername(userInputDTO.username()))
      return ResponseEntity.badRequest().body("Username already taken.");

    if (userRegistrationService.existsByEmail(userInputDTO.email())) {
      return ResponseEntity.badRequest().body("Email already in use.");
    }

    UserOutputDTO createdUserDTO = userRegistrationService.registerUser(
        new UserInputDTO(null, userInputDTO.username(), userInputDTO.email(),
            passwordEncoder.encode(userInputDTO.password()), "student"));

    EntityModel<UserOutputDTO> model = EntityModel.of(createdUserDTO);
    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                                                        .getUserById(createdUserDTO.id()))
                               .withSelfRel());

    return ResponseEntity.created(WebMvcLinkBuilder.linkTo(
                             WebMvcLinkBuilder.methodOn(UserController.class).getUserById(createdUserDTO.id())).toUri())
                         .body(model.toString());
  }

}
