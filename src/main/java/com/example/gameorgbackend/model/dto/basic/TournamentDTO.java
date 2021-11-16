package com.example.gameorgbackend.model.dto.basic;
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

  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date tournamentStart;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date tournamentEnd;

  private Integer reward;

  private boolean isLan;

  private Integer maxTeamSize;

  private Integer maxNumberOfTeams;

  private Integer currentNumberOfTeams;

  private String rules;

  @JsonIgnoreProperties({"tournaments","gameAccounts","teams"})
  private UserDTO organizer;

  @JsonIgnoreProperties("tournament")
  private Set<TeamDTO> teams = new HashSet<>();
}
