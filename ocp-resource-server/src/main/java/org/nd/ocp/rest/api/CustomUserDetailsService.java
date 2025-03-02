package org.nd.ocp.rest.api;

import org.nd.ocp.domain.user.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRegistrationService userRegistrationService;

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    return userRegistrationService.findByUsernameOrEmail(usernameOrEmail)
                                  .map(user -> User.builder()
                                                   .username(user.username())
                                                   .password(user.password())
                                                   .roles(user.roles().toArray(String[]::new))
                                                   .accountExpired(user.accountExpired())
                                                   .accountLocked(user.accountLocked())
                                                   .credentialsExpired(user.credentialsExpired())
                                                   .disabled(!user.enabled())
                                                   .build()
                                  )
                                  .orElseThrow(() -> new UsernameNotFoundException(
                                      "User not found with username/email: " + usernameOrEmail));
  }
}