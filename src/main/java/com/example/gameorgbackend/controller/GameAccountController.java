package com.example.gameorgbackend.controller;

import com.example.gameorgbackend.model.dto.basic.GameAccountDTO;
import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.entity.GameAccount;
import com.example.gameorgbackend.model.service.GameAccountServiceImpl;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameAccountController {
  private final ModelMapper modelMapper;
  private final GameAccountServiceImpl gameAccountService;

  public GameAccountController(ModelMapper modelMapper,
      GameAccountServiceImpl gameAccountService) {
    this.modelMapper = modelMapper;
    this.gameAccountService = gameAccountService;
  }

  @PostMapping(value = "/addGameAccount/{id}")
  public ResponseEntity<GameAccountDTO> addTournament(@PathVariable("id") Long id, @RequestBody GameAccountDTO gameAccountDTO) {
    gameAccountDTO = gameAccountService.create(gameAccountDTO, id);
    if(gameAccountDTO == null)
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(gameAccountDTO, HttpStatus.OK);
  }
  @GetMapping(value = "/getGameAccounts/{id}")
  public ResponseEntity<Collection<GameAccountDTO>> getGameAccounts(@PathVariable("id") Long id) {
    Collection<GameAccountDTO> gameAccounts = gameAccountService.getAll(id);
    return new ResponseEntity<>(gameAccounts, HttpStatus.OK);
  }
}
