package com.example.gameorgbackend.model.dto.specialized;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class LoginFormDTO {

  @NotBlank
  @Length(max = 15)
  private String username;

  @JsonProperty("plainPassword")
  private String password;
}
