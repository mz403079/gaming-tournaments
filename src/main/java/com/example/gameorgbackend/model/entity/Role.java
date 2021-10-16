package com.example.gameorgbackend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer roleId;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;
}
