package com.example.gameorgbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Tournament {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long tournamentId;

  @Column(nullable = false, length = 100, unique = true)
  private String name;

  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime tournamentStart;

  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime tournamentEnd;

  @Column(nullable = false)
  private Integer maxTeamSize;

  @Column(nullable = false)
  private Integer maxNumberOfTeams;

  @Column(nullable = false)
  private Integer currentNumberOfTeams;

  @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<Team> teams = new HashSet<>();
}
