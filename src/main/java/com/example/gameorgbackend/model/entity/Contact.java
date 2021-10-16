package com.example.gameorgbackend.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long contactId;

  @Column(nullable = false, length = 50, unique = true)
  private String emailAddress;

  @Column(nullable = false, length = 20, unique = true)
  private String phoneNumber;

  @Column(nullable = false, length = 80, unique = true)
  private String discordName;
}
