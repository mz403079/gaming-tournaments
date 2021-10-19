package com.example.gameorgbackend.controller;

import com.example.gameorgbackend.model.dto.basic.GameAccountDTO;
import com.example.gameorgbackend.model.dto.basic.TeamDTO;
import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.dto.specialized.TournamentFormDTO;
import com.example.gameorgbackend.model.entity.Team;
import com.example.gameorgbackend.model.service.TeamServiceImpl;
import com.example.gameorgbackend.model.service.TournamentServiceImpl;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TournamentController {
  private final ModelMapper modelMapper;
  private final TournamentServiceImpl tournamentService;
  private final TeamServiceImpl teamService;
  public TournamentController(
      ModelMapper modelMapper,
      TournamentServiceImpl tournamentService,
      TeamServiceImpl teamService) {
    this.modelMapper = modelMapper;
    this.tournamentService = tournamentService;
    this.teamService = teamService;
  }

  @GetMapping(value = "/getTournaments")
  public ResponseEntity<Collection<TournamentDTO>> getTournaments() {
    Collection<TournamentDTO> tournaments = tournamentService.getAll();
    return new ResponseEntity<>(tournaments, HttpStatus.OK);
  }

  @PostMapping(value = "/addTournament")
  public ResponseEntity<TournamentDTO> addTournament(@RequestBody TournamentFormDTO tournamentFormDTO) {
    TournamentDTO tournamentDTO = tournamentService.create(modelMapper.map(tournamentFormDTO, TournamentDTO.class));
    if(tournamentDTO == null)
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(tournamentDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/addTeam")
  public ResponseEntity<TeamDTO> addTeam(@RequestBody TeamDTO teamDTO) {
    for(GameAccountDTO gameAccountDTO : teamDTO.getGameAccounts()) {
      System.out.println(gameAccountDTO.getInGameName());
    }
    teamDTO = teamService.create(teamDTO);
    if(teamDTO == null)
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(teamDTO, HttpStatus.OK);
  }
}
