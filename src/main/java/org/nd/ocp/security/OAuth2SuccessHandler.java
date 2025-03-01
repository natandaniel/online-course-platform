package org.nd.ocp.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.time.Instant;
import java.util.stream.Collectors;

public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private final JwtEncoder jwtEncoder;

  public OAuth2SuccessHandler(JwtEncoder jwtEncoder) {this.jwtEncoder = jwtEncoder;}

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

    Instant now = Instant.now();
    long expiry = 36000L;

    String scope = authentication.getAuthorities()
                                 .stream()
                                 .map(GrantedAuthority::getAuthority)
                                 .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
                                      .issuer("self")
                                      .issuedAt(now)
                                      .expiresAt(now.plusSeconds(expiry))
                                      .subject(oauthUser.getAttribute("login"))
                                      .claim("scope", scope)
                                      .build();

    String jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    response.sendRedirect("http://localhost:3000/auth/success?token=" + jwtToken);
  }
}