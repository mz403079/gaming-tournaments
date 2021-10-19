package com.example.gameorgbackend.model.dto.basic;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {
  private Long gameId;

  @Column(nullable = false, length = 100)
  private String name;
}
