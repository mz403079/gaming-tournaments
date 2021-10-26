package com.example.gameorgbackend.model.dto.basic;

import com.example.gameorgbackend.model.entity.Tournament;
import com.example.gameorgbackend.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {

  private Long teamId;

  private String teamName;

  @JsonIgnoreProperties({"teams", "organizer"})
  private TournamentDTO tournament;

  @JsonIgnoreProperties({"teams","tournaments"})
  private Set<UserDTO> players = new HashSet<>();
}
