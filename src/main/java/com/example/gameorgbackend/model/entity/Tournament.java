package com.example.gameorgbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.Date;
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

  private String description;

  private Integer reward;

  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean isLan;

  private String city;

  private String street;

  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private Date tournamentStart;

  @Column(nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date tournamentEnd;

  @Column(nullable = false)
  private Integer maxTeamSize;

  @Column(nullable = false)
  private Integer maxNumberOfTeams;

  @Column(nullable = false)
  private Integer currentNumberOfTeams;

  private String rules;

  private String winner;

  @JsonIgnoreProperties({"tournaments","gameAccounts","teams"})
  @ManyToOne(fetch = FetchType.LAZY)
  private User organizer;

  @JsonIgnoreProperties("tournament")
  @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<Team> teams = new HashSet<>();
}
