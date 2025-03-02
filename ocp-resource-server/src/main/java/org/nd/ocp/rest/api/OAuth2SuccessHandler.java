package org.nd.ocp.rest.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    String redirectUri = "http://localhost:3000/auth/callback";

    String authorizationUrl =
        "http://localhost:9000/oauth2/authorize" + "?client_id=frontend-client" +
            "&response_type=code" + "&redirect_uri=" +
            URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);

    response.sendRedirect(authorizationUrl);
  }
}