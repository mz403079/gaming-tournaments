package com.example.gameorgbackend.model.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gameId;

  @Column(nullable = false, length = 100)
  private String name;
}
