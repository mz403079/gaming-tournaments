package com.example.gameorgbackend.model.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "\"user\"")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long userId;

  @Column(nullable = false, length = 50, unique = true)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Column(nullable = false, unique = true)
  @JsonIgnore
  private String email;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 50)
  private String surname;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Contact contact;

  @JsonIgnoreProperties("user")
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<GameAccount> gameAccounts = new HashSet<>();

  @JsonIgnoreProperties("organizer")
  @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<Tournament> tournaments = new HashSet<>();

  @JsonIgnoreProperties("players")
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//  @JoinTable(name = "users_teams",
//      joinColumns = {
//          @JoinColumn(name = "users_teams_user_id", referencedColumnName = "user_id",
//              nullable = false, updatable = false)},
//      inverseJoinColumns = {
//          @JoinColumn(name = "users_teams_team_id", referencedColumnName = "team_id",
//              nullable = false, updatable = false)})
  private  Set<Team> teams = new HashSet<>();

  @Column(nullable = false)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> Roles = new HashSet<>();


  public void addTeam(Team team) {
    this.teams.add(team);
    team.getPlayers().add(this);
  }

  public void removeTeam(Team team) {
    this.teams.remove(team);
    team.getPlayers().remove(this);
  }
}
