package com.example.gameorgbackend.controller;

import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.dto.specialized.TournamentFormDTO;
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
  public TournamentController(
      ModelMapper modelMapper,
      TournamentServiceImpl tournamentService) {
    this.modelMapper = modelMapper;
    this.tournamentService = tournamentService;
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
}
