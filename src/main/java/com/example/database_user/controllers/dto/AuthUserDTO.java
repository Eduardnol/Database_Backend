package com.example.database_user.controllers.dto;

import com.example.database_user.controllers.enums.Role;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@Builder
public class AuthUserDTO implements UserDetails {

  @Required
  private String id;
  @Required
  private String name;
  @Required
  private String surname;
  @Required
  private String email;
  @Required
  private Role role;

  //Password
  @Required
  private String password;
  @Singular("passwordHistory")
  private List<String> passwordHistory;
  private LocalDateTime passwordUpdatedAt;

  //Login
  private LocalDateTime createdAt;
  @Singular("loginHistory")
  private List<LocalDateTime> loginHistory;
  @Singular("loginIpHistory")
  private List<String> loginIpHistory;
  private boolean isLocked;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
