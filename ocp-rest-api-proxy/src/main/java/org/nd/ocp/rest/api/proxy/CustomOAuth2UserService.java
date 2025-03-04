package org.nd.ocp.rest.api.proxy;

import org.nd.ocp.domain.user.UserInputDTO;
import org.nd.ocp.domain.user.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  @Autowired
  private UserRegistrationService userRegistrationService;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    String provider = userRequest.getClientRegistration().getRegistrationId();
    String email = oAuth2User.getAttribute("email");
    String username = oAuth2User.getAttribute("login");

    if (email == null && provider.equalsIgnoreCase("github"))
      email = fetchGitHubEmail(userRequest.getAccessToken().getTokenValue());

    if (userRegistrationService.findByUsernameOrEmail(email).isEmpty())
      userRegistrationService.registerUser(
          new UserInputDTO(username, email, "", provider, List.of("student")));

    return oAuth2User;
  }

  private String fetchGitHubEmail(String accessToken) {
    String emailApiUrl = "https://api.github.com/user/emails";

    RestClient client = RestClient.create();

    List<Map<String, Object>> response = client.get()
                                               .uri(emailApiUrl)
                                               .headers(
                                                   headers -> headers.setBearerAuth(accessToken))
                                               .retrieve()
                                               .body(new ParameterizedTypeReference<>() {}); // Use

    if (response != null && !response.isEmpty()) {
      for (Map<String, Object> emailData : response) {
        Boolean primary = (Boolean) emailData.get("primary");
        if (primary != null && primary) {
          return (String) emailData.get("email");
        }
      }
    }

    throw new RuntimeException("Primary email not found in GitHub response");
  }

}