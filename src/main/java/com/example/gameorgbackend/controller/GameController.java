package com.example.gameorgbackend.controller;

import com.example.gameorgbackend.model.dto.basic.GameDTO;
import com.example.gameorgbackend.model.service.GameServiceImpl;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

  private final GameServiceImpl gameService;

  public GameController(GameServiceImpl gameService) {
    this.gameService = gameService;
  }


  @GetMapping(value = "/getGames")
  public ResponseEntity<Collection<GameDTO>> getGames() {
    Collection<GameDTO> games = gameService.getAll();
    return new ResponseEntity<>(games, HttpStatus.OK);
  }
}
