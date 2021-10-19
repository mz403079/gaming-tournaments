package com.example.gameorgbackend.model.dto.specialized;

import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentFormDTO {

  private String name;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date tournamentStart;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date tournamentEnd;

  private Integer maxTeamSize;

  private Integer maxNumberOfTeams;

  private String regulations;

  private UserDTO organizer;
}
