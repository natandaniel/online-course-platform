package org.nd.ocp.rest.api.proxy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  @Autowired
  private RestClient restClient;

  @Value("${restapi.users-url}")
  private String usersBaseUrl;

  @GetMapping("/{userId}")
  public ResponseEntity<EntityModel<WebUserOutputDTO>> getUserById(@PathVariable int userId) {
    String url = UriComponentsBuilder.fromHttpUrl(usersBaseUrl)
                                     .pathSegment(String.valueOf(userId)) // Append /{userId}
                                     .toUriString();

    try {
      EntityModel<WebUserOutputDTO> entityModel = restClient.get()
                                                            .uri(url)
                                                            .retrieve()
                                                            .body(
                                                                ParameterizedTypeReference.forType(
                                                                    WebUserOutputDTO.class));

      return ResponseEntity.ok(entityModel);

    }
    catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/register")
  public ResponseEntity<EntityModel<WebUserOutputDTO>> registerUser(@RequestParam String username,
      @RequestParam String email, @RequestParam String password) {

    String url =
        UriComponentsBuilder.fromHttpUrl(usersBaseUrl)
                            .pathSegment("register") // Append /register
                            .queryParam("username", username)
                            .queryParam("email", email)
                            .queryParam("password", password)
                            .toUriString();

    try {
      EntityModel<WebUserOutputDTO> entityModel = restClient.post()
                                                            .uri(url)
                                                            .retrieve()
                                                            .body(
                                                                ParameterizedTypeReference.forType(
                                                                    WebUserOutputDTO.class));

      return ResponseEntity.created(UriComponentsBuilder.fromHttpUrl(usersBaseUrl)
                                                        .pathSegment(String.valueOf(
                                                            entityModel.getContent().id()))
                                                        .build()
                                                        .toUri()).body(entityModel);

    }
    catch (Exception e) {
      return ResponseEntity.status(500).build();
    }
  }
}