package com.example.gameorgbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GameAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long gameAccountId;

  @Column(nullable = false, length = 100)
  private String inGameName;

  @JsonIgnoreProperties("gameAccounts")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @OneToOne
  private Game game;

}
