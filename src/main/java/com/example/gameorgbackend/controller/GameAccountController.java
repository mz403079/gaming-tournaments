package com.example.gameorgbackend.controller;

import com.example.gameorgbackend.model.dto.basic.GameAccountDTO;
import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.dto.specialized.MessageResponse;
import com.example.gameorgbackend.model.entity.GameAccount;
import com.example.gameorgbackend.model.service.GameAccountServiceImpl;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ResponseEntity<MessageResponse> addTournament(@PathVariable("id") Long id, @RequestBody GameAccountDTO gameAccountDTO) {
    gameAccountDTO = gameAccountService.create(gameAccountDTO, id);
    if(gameAccountDTO == null)
      return new ResponseEntity<>(new MessageResponse("name taken"), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(new MessageResponse("ok"), HttpStatus.OK);
  }

  @DeleteMapping(value = "/deleteGameAccount/{id}")
  public ResponseEntity<String> addTournament(@PathVariable("id") Long id) {
      gameAccountService.delete(id);
    return new ResponseEntity<>("Success", HttpStatus.OK);
  }

  @GetMapping(value = "/getGameAccounts/{id}")
  public ResponseEntity<Collection<GameAccountDTO>> getGameAccounts(@PathVariable("id") Long id) {
    Collection<GameAccountDTO> gameAccounts = gameAccountService.getAll(id);
    return new ResponseEntity<>(gameAccounts, HttpStatus.OK);
  }
}
