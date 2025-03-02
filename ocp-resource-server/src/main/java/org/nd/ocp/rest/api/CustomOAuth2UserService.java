package org.nd.ocp.rest.api;

import org.nd.ocp.domain.user.UserInputDTO;
import org.nd.ocp.domain.user.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
      userRegistrationService.registerUser(new UserInputDTO(username, email,
          "", provider, List.of("student")));

    return oAuth2User;
  }

  private String fetchGitHubEmail(String accessToken) {
    String emailApiUrl = "https://api.github.com/user/emails";
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<List> response =
        restTemplate.exchange(emailApiUrl, HttpMethod.GET, entity, List.class);

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
      List<Map<String, Object>> emails = response.getBody();

      for (Map<String, Object> emailData : emails) {
        boolean isPrimary = (boolean) emailData.get("primary");
        boolean isVerified = (boolean) emailData.get("verified");

        if (isPrimary && isVerified)
          return (String) emailData.get("email");
      }
    }

    return null;
  }
}