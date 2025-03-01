package org.nd.ocp.adapters.web;

import jakarta.validation.constraints.NotNull;
import org.nd.ocp.domain.user.UserInputDTO;
import org.nd.ocp.domain.user.UserManagementService;
import org.nd.ocp.domain.user.UserOutputDTO;
import org.nd.ocp.domain.user.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
  public ResponseEntity<EntityModel<WebUserOutputDTO>> getUserById(@PathVariable int userId) {
    Optional<UserOutputDTO> optUserOutputDTO = userManagementService.findById(userId);

    if (optUserOutputDTO.isEmpty()) return ResponseEntity.notFound().build();
    UserOutputDTO userOutputDTO = optUserOutputDTO.get();

    WebUserOutputDTO webUserOutputDTO = new WebUserOutputDTO(userOutputDTO.id(),
        userOutputDTO.username(), userOutputDTO.email(), userOutputDTO.createdAt(),
        userOutputDTO.updatedAt());

    EntityModel<WebUserOutputDTO> model = EntityModel.of(webUserOutputDTO);
    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                                                        .getUserById(userId))
                               .withSelfRel());

    return ResponseEntity.ok(model);
  }

  @PostMapping("/register")
  public ResponseEntity<EntityModel<WebUserOutputDTO>> registerStudent(
      @NotNull @RequestParam String username,
      @NotNull @RequestParam String email,
      @NotNull @RequestParam String password) {
    UserOutputDTO createdUserDTO = userRegistrationService.registerUser(
        new UserInputDTO(username, email, passwordEncoder.encode(password),
            "local", List.of("student")));

    WebUserOutputDTO webUserOutputDTO = new WebUserOutputDTO(createdUserDTO.id(),
        createdUserDTO.username(), createdUserDTO.email(), createdUserDTO.createdAt(),
        createdUserDTO.updatedAt());

    EntityModel<WebUserOutputDTO> model = EntityModel.of(webUserOutputDTO);
    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                                                        .getUserById(createdUserDTO.id()))
                               .withSelfRel());

    return ResponseEntity.created(WebMvcLinkBuilder.linkTo(
                             WebMvcLinkBuilder.methodOn(UserController.class).getUserById(createdUserDTO.id())).toUri())
                         .body(model);
  }

}
