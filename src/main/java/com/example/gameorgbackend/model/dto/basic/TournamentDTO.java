package com.example.gameorgbackend.model.dto.basic;

import com.example.gameorgbackend.model.entity.Team;
import com.example.gameorgbackend.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentDTO {

  private Long tournamentId;

  private String name;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date tournamentStart;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date tournamentEnd;

  private Integer maxTeamSize;

  private Integer maxNumberOfTeams;

  private Integer currentNumberOfTeams;

  private String regulations;

  @JsonIgnoreProperties({"tournaments","gameAccounts","teams"})
  private UserDTO organizer;

  @JsonIgnoreProperties("tournament")
  private Set<TeamDTO> teams = new HashSet<>();
}
