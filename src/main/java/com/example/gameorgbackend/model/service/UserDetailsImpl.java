package com.example.gameorgbackend.model.service;
import com.example.gameorgbackend.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUTD = 1L;

  private long userId;

  private String username;

  private String email;

  private String name;

  private String surname;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long userId, String username, String email, String password, String name, String surname,
      Collection<? extends GrantedAuthority> authorities) {
    this.userId = userId;
    this.username = username;
    this.email = email;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getUserId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        user.getName(),
        user.getSurname(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
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