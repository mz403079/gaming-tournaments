package com.example.gameorgbackend.model.dto.basic;

import com.example.gameorgbackend.model.entity.Tournament;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {

  private Long teamId;

  private String teamName;

  @JsonIgnore
  private Tournament tournament;

  private Set<GameAccountDTO> gameAccounts = new HashSet<>();
}
