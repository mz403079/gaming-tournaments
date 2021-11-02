package com.example.gameorgbackend.model.dto.specialized;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterFormDTO {

  @NotBlank
  @Length(max = 15)
  private String username;

  @NotBlank
  @JsonProperty("plainPassword")
  private String password;

  @NotBlank
  @Length(max = 50)
  @Email
  private String email;

  @NotBlank
  private String name;

  @NotBlank
  private String surname;
}