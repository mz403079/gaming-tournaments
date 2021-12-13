package com.example.gameorgbackend.model.dto.specialized;

import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentFormDTO {

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

  private String city;

  private String street;

  private Double lat;

  private Double lng;

  private String rules;

  private UserDTO organizer;
}
