package com.example.database_user.model.dto;

import com.example.database_user.model.enums.Role;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
  private Boolean isLocked;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Role roles = this.getRole();
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    authorities.add(new SimpleGrantedAuthority(role.name()));

    return authorities;
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
