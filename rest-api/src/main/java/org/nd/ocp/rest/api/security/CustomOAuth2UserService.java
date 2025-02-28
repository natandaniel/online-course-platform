package org.nd.ocp.rest.api.security;

import org.nd.ocp.rest.api.domain.user.UserInputDTO;
import org.nd.ocp.rest.api.domain.user.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  @Autowired
  private UserRegistrationService userRegistrationService;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    String provider =
        userRequest.getClientRegistration().getRegistrationId().toUpperCase();
    String email = oAuth2User.getAttribute("email");
    String username = oAuth2User.getAttribute("login");

    if (userRegistrationService.findByUsernameOrEmail(email).isEmpty())
      userRegistrationService.registerUser(new UserInputDTO(username, email,
          "", provider, List.of("student")));

    return oAuth2User;
  }
}