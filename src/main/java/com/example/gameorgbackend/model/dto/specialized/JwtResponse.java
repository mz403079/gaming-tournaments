package com.example.gameorgbackend.model.dto.specialized;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private String name;
  private String surname;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, String email, String name, String surname, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.roles = roles;
  }
}