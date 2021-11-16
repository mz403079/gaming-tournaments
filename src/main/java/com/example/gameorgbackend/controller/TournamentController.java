package com.example.gameorgbackend.controller;

import com.example.gameorgbackend.model.dto.basic.TeamDTO;
import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.example.gameorgbackend.model.dto.specialized.TeamFormDTO;
import com.example.gameorgbackend.model.dto.specialized.TournamentFormDTO;
import com.example.gameorgbackend.model.entity.Tournament;
import com.example.gameorgbackend.model.repository.TournamentRepository;
import com.example.gameorgbackend.model.service.TeamServiceImpl;
import com.example.gameorgbackend.model.service.TournamentServiceImpl;
import com.example.gameorgbackend.model.service.UserServiceImpl;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sipios.springsearch.anotation.SearchSpec;

@RestController
@RequestMapping("/api")
public class TournamentController {
  private final ModelMapper modelMapper;
  private final TournamentServiceImpl tournamentService;
  private final TeamServiceImpl teamService;
  private final UserServiceImpl userService;
  private final TournamentRepository tournamentRepository;

  public TournamentController(
      ModelMapper modelMapper,
      TournamentServiceImpl tournamentService,
      TeamServiceImpl teamService,
      UserServiceImpl userService,
      TournamentRepository tournamentRepository) {
    this.modelMapper = modelMapper;
    this.tournamentService = tournamentService;
    this.teamService = teamService;
    this.userService = userService;
    this.tournamentRepository = tournamentRepository;
  }

  @GetMapping("/tournaments/searchUrl")
  public ResponseEntity<Collection<TournamentDTO>> getTournamentsFilter(
      @SearchSpec Specification<Tournament> specs) {
    Collection<TournamentDTO> tournaments = tournamentService.getAllQuery(specs);
    return new ResponseEntity<>(tournaments, HttpStatus.OK);
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
  public ResponseEntity<TeamDTO> addTeam(@RequestBody TeamFormDTO teamFormDTO) {
    TeamDTO teamDTO = modelMapper.map(teamFormDTO, TeamDTO.class);
    Set<UserDTO> players = new HashSet<>();
    for (UserDTO player : teamDTO.getPlayers()) {
      players.add(userService.getByUsername(player.getUsername()));
    }
    teamDTO.setPlayers(players);
    teamDTO = teamService.create(teamDTO);
    if(teamDTO == null)
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(teamDTO, HttpStatus.OK);
  }
}
