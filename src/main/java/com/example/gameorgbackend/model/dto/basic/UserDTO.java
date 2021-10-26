package com.example.gameorgbackend.model.dto.basic;

import com.example.gameorgbackend.model.entity.Contact;
import com.example.gameorgbackend.model.entity.GameAccount;
import com.example.gameorgbackend.model.entity.Role;
import com.example.gameorgbackend.model.entity.Team;
import com.example.gameorgbackend.model.entity.Tournament;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private Long userId;

  @Column(nullable = false, length = 50, unique = true)
  private String username;

  @Column(nullable = false, length = 16)
  @JsonIgnore
  private String password;

  @Column(nullable = false, unique = true)
  @JsonIgnore
  private String email;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 50)
  private String surname;

  private ContactDTO contact;

  @JsonIgnoreProperties("user")
  private Set<GameAccountDTO> gameAccounts;

  @JsonIgnoreProperties("organizer")
  private Set<TournamentDTO> tournaments;

  @JsonIgnoreProperties("players")
  private  Set<TeamDTO> teams;

  @Column(nullable = false)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> Roles;
}
