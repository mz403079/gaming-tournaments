package com.example.gameorgbackend.model.dto.specialized;

import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.entity.Tournament;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamFormDTO {
  private Long teamId;

  private String teamName;

//  @JsonIgnore
  private TournamentDTO tournament;

  private Set<String> players = new HashSet<>();
}
