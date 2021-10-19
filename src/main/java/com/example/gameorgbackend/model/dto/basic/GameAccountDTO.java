package com.example.gameorgbackend.model.dto.basic;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameAccountDTO {

  private Long gameAccountId;

  @Column(nullable = false, length = 100)
  private String inGameName;

  private TeamDTO team;

  private GameDTO gameDTO;

  private UserDTO user;
}
