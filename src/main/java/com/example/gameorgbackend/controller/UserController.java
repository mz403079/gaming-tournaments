package com.example.gameorgbackend.controller;

import com.example.gameorgbackend.model.dto.basic.GameAccountDTO;
import com.example.gameorgbackend.model.dto.basic.GameDTO;
import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.example.gameorgbackend.model.dto.basic.UserRankingDTO;
import com.example.gameorgbackend.model.dto.specialized.UserInfoDTO;
import com.example.gameorgbackend.model.service.UserServiceImpl;
import java.util.Collection;
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
public class UserController {
  final private UserServiceImpl userService;

  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/updateUser/{id}")
  public ResponseEntity<UserInfoDTO> updateUserInfo(@PathVariable("id") Long userID,
      @RequestBody UserInfoDTO userInfoDTO) {
    userInfoDTO = userService.update(userInfoDTO, userID);
    if(userInfoDTO == null)
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/getUserInfo/{id}")
  public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable("id") Long id) {
    UserInfoDTO userInfoDTO = userService.getUserInfo(id);
    return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/getRanking")
  public ResponseEntity<Collection<UserRankingDTO>> getRanking() {
    Collection<UserRankingDTO> users = userService.getRanking();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
