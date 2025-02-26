package org.nd.online_course_platform.controllers;

import org.nd.online_course_platform.domain.user.UserInputDTO;
import org.nd.online_course_platform.domain.user.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {
  @Autowired
  private UserRegistrationService userRegistrationService;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    Map<String, Object> attributes = oAuth2User.getAttributes();

    String email = (String) attributes.get("email");
    String username = (String) attributes.get("login");
    String provider = userRequest.getClientRegistration().getRegistrationId();

    if (!userRegistrationService.existsByEmail(email))
      userRegistrationService.registerUser(
          new UserInputDTO(null, username, email, "", provider, "student"));

    return new DefaultOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT")), attributes, "id");
  }
}